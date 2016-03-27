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

import java.lang.StringBuilder;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.sql.Date;

public class Deed {
  private String id;
  private String description;
  private Date date;
  private String  reqUserId;
  private Set<String> claimedUserIds = Collections.synchronizedSet(new HashSet<String>());
  private Location location = new Location();

  public Deed(String id, String desc, long t, String uid, Location l) {
    this.id = id;
    this.description = desc;
    this.date = new Date(t);
    this.reqUserId = uid;
    this.location = l;
  };

  public Deed(String id, String desc, String d, String uid, Location l) {
    this.id = id;
    this.description = desc;
    this.date = Date.valueOf(d);
    this.reqUserId = uid;
    this.location = l;
    this.claimedUserIds = Collections.synchronizedSet(new HashSet<String>());
  };

  public String getId() { return this.id; };
  public void setId(String id) { this.id = id; };

  public String getDescription() { return this.description; };
  public void setDescription(String desc) { this.description = desc; };

  public String getDate() { return this.date.toString(); };
  public void setDate(long t) { this.date = new Date(t); };
  public void setDate(String d) { this.date = Date.valueOf(d); };

  public String getRequestingUserId() { return this.reqUserId; };
  public void setRequestingUserId(String uid) { this.reqUserId = uid; };

  public Set<String> getClaimedUserIds() { return this.claimedUserIds; };
  public void addClaimedUserId(String uid) { this.claimedUserIds.add(uid); };
  public void deleteClaimedUserId(String uid) { this.claimedUserIds.remove(uid); };

  public Location getLocation() { return this.location; };
  public void setLocation(Location loc) { this.location = loc; };

  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append(String.format("ID: %s\n", this.id));
    s.append(String.format("Description: %s\n", this.description));
    s.append(String.format("Requesting User ID: %s\n", this.reqUserId));
    s.append(String.format("Date: %s\n", this.date.toString()));
    s.append(String.format("Claimed User IDs: %s\n", this.claimedUserIds.toString()));
    s.append(String.format("Location: %s\n", this.location.toString()));
    return s.toString();
  }

  public boolean equals(Deed d) {
    if(!this.id.equals(d.getId())) { return false; };
    if(!this.description.equals(d.getDescription()))  { return false; };
    if(!this.reqUserId.equals(d.getRequestingUserId()))  { return false; };
    if(!this.date.equals(d.getDate()))  { return false; };
    if(!this.claimedUserIds.equals(d.getClaimedUserIds())) { return false; };
    if(!this.location.equals(d.getLocation()))  { return false; };

    return true;
  }

  public void copy(Deed d) {
      this.id = d.getId();
      this.description = d.getDescription();
      this.reqUserId = d.getRequestingUserId();
      this.date = Date.valueOf(d.getDate());
      this.claimedUserIds.addAll(d.getClaimedUserIds());
      this.location.copy(d.getLocation());
  }
}