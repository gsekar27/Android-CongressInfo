package com.sunlightfoundation.congressinfo;

import java.io.Serializable;

/**
 * Created by ganmanisekar on 11/17/16.
 */
public class LegislatorMemebers implements Serializable {

    private static final long serialVersionUID = 1L;

    private String bioguide_id;
    private String title;
    private String chamber;
    private String first_name;
    private String last_name;
    private String state_name;
    private String state;



    private String term_start;
    private String term_end;
    private String oc_email;
    private String office;
    private String party;
    private String phone;
    private String birthday;
    private String district;
    private String fax;
    private String website;
    private String facebook_id;
    private String twitter_id;




    public String getBioguide_id(){
        return this.bioguide_id;
    }

    public String getchamber(){

        return this.chamber;
    }
    public String getFirst_name(){
        return this.first_name;
    }
    public String getLast_name(){
        return this.last_name;
    }
    public String getState_name() {
        return state_name;
    }

    public String getState() {
        return state;
    }

    public String getTitle() {
        return title;
    }

    public String getChamber() {
        return chamber;
    }

    public String getTerm_start() {
        return term_start;
    }

    public String getTerm_end() {
        return term_end;
    }

    public String getOc_email() {
        return oc_email;
    }

    public String getOffice() {
        return office;
    }

    public String getParty() {
        return party;
    }

    public String getPhone() {
        return phone;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getDistrict() {
        return district;
    }

    public String getFax() {
        return fax;
    }

    public String getWebsite() {
        return website;
    }

    public String getFacebook_id() {
        return facebook_id;
    }

    public String getTwitter_id() {
        return twitter_id;
    }




}
