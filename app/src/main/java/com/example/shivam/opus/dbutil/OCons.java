package com.example.shivam.opus.dbutil;

/**
 * Created by Shivam on 7/23/2017.
 */

     public class OCons {
            public static final String DB_Name="OpusD";
            public static final int DB_Version=1;
            public static final String LTable="Login";
            public static final String CTable="Category";
            public static final String BTable="Book";
            public static final String MTable="Member";
            public static final String ITable="Issue";
            //login
            public static final String UId="UserID";
            public static final String UType="Usertype";
            public static final String UPass="UserPass";
            public static final String LQuery=
                    "create table "+LTable+"("+UId+" text primary key,"+UPass+" text not null,"+UType+" text not null)";
            //category
            public static final String CId="CatID";
            public static final String CName="CatName";
            public static final String CQuery=
                    "create table "+CTable+"("+CId+" text primary key,"+CName+" text not null)";
           //Book
            public static final String BCId="BCatID";
            public static final String BName="BookName";
            public static final String BTCopies="TotalCopies";
            public static final String BId="BookID";
            public static final String BRCopies="RemainingCopies";
            public static final String BQuery=
                    "create table "+BTable+"("+BId+" text primary key,"+BName+" text not null,"+BTCopies+" integer not null,"+BRCopies+" integer not null,"
                            +BCId+" text,foreign key("+BCId+") references "+CTable+"("+CId+"))";
            //Member
            public static final String MId="MemberId";
            public static final String MName="MemberName";
            public static final String MEmail="MemberEmail";
            public static final String MAdd="MemberAdd";
            public static final String MPhN="MemberPhone";
            public static final String MDob="MemberDob";
            public static final String MFee="MembershipFee";
            public static final String MQuery=
                    "create table "+MTable+"("+MId+" text primary key,"+MName+" text not null,"+MEmail+" text null,"+MAdd+" text not null,"+MPhN+" integer not null,"+MDob+" integer not null,"+MFee+" integer not null)";
            //Issue
            public static final String ICID="CatId";
            public static final String IMId="MemberId";
            public static final String IRentDate="DateOfRent";
            public static final String IReturnDate="DateOfReturn";
            public static final String IActualReturnD="ActualDateOfReturn";
            public static final String InvoiceNo="InvoiceNo";
            public static final String IBId="BookId";
            public static final String ITCost="TotalCost";
            public static final String IQuery=
                    "create table "+ITable+"("+InvoiceNo+" integer primary key autoincrement,"+ICID+" text not null,"+IMId+" text,"+IRentDate+" text not null,"+IReturnDate+" integer not null,"
                    +IActualReturnD+" integer null,"+IBId+" text not null,"+ITCost+" integer not null,foreign key("+IMId+") references "+MTable+"("+MId+"))";

}
