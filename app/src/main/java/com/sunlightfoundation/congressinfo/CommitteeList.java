package com.sunlightfoundation.congressinfo;

import java.io.Serializable;

/**
 * Created by ganmanisekar on 11/17/16.
 */
public class CommitteeList implements Serializable {

    private static final long serialVersionUID = 1L;

    private String chamber;
    private String committee_id;
    private String name;
    private String office;
    private String phone;



    private String parent_committee_id;

    public String getChamber() {
        return chamber;
    }

    public String getCommittee_id() {
        return committee_id;
    }

    public String getName() {
        return name;
    }

    public String getOffice() {
        return office;
    }

    public String getPhone() {
        return phone;
    }

    public String getParent_committee_id() {
        return parent_committee_id;
    }


}
