package com.sunlightfoundation.congressinfo;

import java.io.Serializable;

/**
 * Created by ganmanisekar on 11/17/16.
 */
public class BillsList implements Serializable {

    private static final long serialVersionUID = 1L;

    private String bill_id;
    private String bill_type;
    private String chamber;
    private Boolean active;
    private String introduced_on;
    private String official_title;
    private String short_title;
    private String sponsor_id;
    private sponsor sponsor;



    private history history;
    private urls    urls;


    public BillsList.sponsor getSponsor() {
        return sponsor;
    }

    public BillsList.history getHistory() {
        return history;
    }

    public BillsList.urls getUrls() {
        return urls;
    }



    public class sponsor implements Serializable{


        private String first_name;
        private String last_name;
        private String title;

        public String getFirst_name() {
            return first_name;
        }

        public String getLast_name() {
            return last_name;
        }

        public String getTitle() {
            return title;
        }
    }

    public class history implements Serializable{

        private Boolean active;

        public Boolean getActive() {
            return active;
        }
    }

    public class urls implements  Serializable{
        private String congress;
        private String opencongress;

        public String getCongress() {
            return congress;
        }

        public String getOpencongress() {
            return opencongress;
        }
    }

    public String getBill_id() {
        return bill_id;
    }

    public String getBill_type() {
        return bill_type;
    }

    public String getChamber() {
        return chamber;
    }

    public Boolean getActive() {
        return active;
    }

    public String getIntroduced_on() {
        return introduced_on;
    }

    public String getOfficial_title() {
        return official_title;
    }
    public String getShort_title() {
        return short_title;
    }

    public String getSponsor_id() {
        return sponsor_id;
    }


}
