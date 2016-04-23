package core;
/**
 *
 *  Module Name: Deed Service
 *
 *  Description: The Deed Service is a generic service which allows creation,
 *  modification, and deletion of deeds. The User service is where Deed
 *  association happens.
 *
 *  Date: 3/25/16
 *
 *  Author: Joseph DeStefanis
 */

import core.Location;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Field;

import java.lang.StringBuilder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Deed {
  private String id;
  private String description;
  private Date date;
  private String  requestingUserId;
  private Set<String> claimedUserIds = Collections.synchronizedSet(new HashSet<String>());
  private Location location = new Location();

  @Transient
  private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
  private boolean active;
  @Field("pValue")
  private int pointValue;

  public Deed() {};

  public Deed(String id, String desc, long t, String uid, Location l, int points) {
    this.id = id;
    this.description = desc;
    this.date = new Date(t);
    this.requestingUserId = uid;
    this.location.copy(l);
    this.pointValue = points;
  };

  public Deed(String id, String desc, String d, String uid, Location l, int points) {
    this.id = id;
    this.description = desc;
    try {
      this.date = df.parse(d);
    } catch(ParseException ex) {
      System.out.println(ex.getMessage());
    }
    this.requestingUserId = uid;
    this.location.copy(l);
    this.claimedUserIds = Collections.synchronizedSet(new HashSet<String>());

    this.pointValue = points;
  };

  public Deed(Deed d) {
    this.copy(d);
  }

  public String getId() { return this.id; };
  public void setId(String id) { this.id = id; };

  public String getDescription() { return this.description; };
  public void setDescription(String desc) { this.description = desc; };

  public String getDate() { return this.date.toString(); };
  public void setDate(long t) { this.date.setTime(t); };

  public void setDate(String d) {
    try {
      this.date = df.parse(d);
    } catch(ParseException ex) {
      System.out.println(ex.getMessage());
    }
  };

  public String getRequestingUserId() { return this.requestingUserId; };
  public void setRequestingUserId(String uid) { this.requestingUserId = uid; };

  public Set<String> getClaimedUserIds() { return this.claimedUserIds; };
  public void addClaimedUserId(String uid) { this.claimedUserIds.add(uid); };
  public void deleteClaimedUserId(String uid) { this.claimedUserIds.remove(uid); };

  public Location getLocation() { return this.location; };
  public void setLocation(Location loc) { this.location.copy(loc); };

  public boolean isActive() { return active; }

  public void setActive(boolean active) { this.active = active; }

  public int getPointValue() { return pointValue; }

  public void setPointValue(int pointValue) { this.pointValue = pointValue; }

  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append(String.format("ID: %s\n", this.id));
    s.append(String.format("Description: %s\n", this.description));
    s.append(String.format("Requesting User ID: %s\n", this.requestingUserId));
    s.append(String.format("Date: %s\n", this.date.toString()));
    s.append(String.format("Claimed User IDs: %s\n", this.claimedUserIds.toString()));
    s.append(String.format("Location: %s\n", this.location.toString()));
    s.append(String.format("Point Value: %s\n", this.location.toString()));
    return s.toString();
  }

  public boolean equals(Deed d) {
    if(this.id.equals(d.id) &&
       this.description.equals(d.description) &&
       this.requestingUserId.equals(d.requestingUserId) &&
       this.date.equals(d.date) &&
       this.claimedUserIds.equals(d.claimedUserIds) &&
       this.location.equals(d.location)) {
      return true;
    } else {
      return false;
    }
  }

  public void copy(Deed d) {
      this.id = d.id;
      this.description = d.description;
      this.requestingUserId = d.requestingUserId;
      this.date = d.date;
      this.claimedUserIds = d.claimedUserIds;
      this.location = d.location;
  }

}