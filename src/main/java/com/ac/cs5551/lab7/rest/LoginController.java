package com.ac.cs5551.lab7.rest;

import java.net.UnknownHostException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

/**
 * @author ac010168
 *
 */
@RestController
@RequestMapping("/login")
public class LoginController {

  @RequestMapping(method = RequestMethod.GET, produces="application/json;charset=UTF-8")
  public Message login(@RequestParam(value="name") String name, 
                      @RequestParam(value="pass") String pass) {
    if (name == null || name.trim().length() == 0)
      return new Message("Error:  No Name Parameter Value Provided");
    if (pass == null || pass.trim().length() == 0)
      return new Message("Error:  No Pass Parameter Value Provided");
    
    MongoClient client = null;
    try {
      client = new MongoClient("localhost", 27017);
    } catch (UnknownHostException e) {
      e.printStackTrace();
      return new Message("Error:  Database Unavailable!");
    }
    
    DB mongoDB = client.getDB("lab7");
    DBCollection userCollection = mongoDB.getCollection("users");
    
    //Let's just query for username, then validate password, so we can generate distinct messages
    BasicDBObject queryObject = new BasicDBObject();
    queryObject.append("user", name);
    
    DBCursor results = userCollection.find(queryObject);
    while (results.hasNext()) {
      DBObject resultObject = results.next();
      if (resultObject.containsField("password")) {
        String dbPassword = (String)resultObject.get("password");
        if (dbPassword.compareTo(pass) == 0) {
          try { results.close(); } catch (Throwable t) { /** Ignore Close Errors */ }
          try { client.close();  } catch (Throwable t) { /** Ignore Close Errors */ }
          
          return new Message("Welcome, " + name + "!");
        } else {
          try { results.close(); } catch (Throwable t) { /** Ignore Close Errors */ }
          try { client.close();  } catch (Throwable t) { /** Ignore Close Errors */ }
          
          return new Message("Error:  Password Incorrect!");
        }
      }
    }
    
    try { client.close();  } catch (Throwable t) { /** Ignore Close Errors */ }
    
    return new Message("Error:  User Not Found!");
  }
}
