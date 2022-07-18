package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingPojo {
    /*
       1) Tüm keyler için Private veriable'lar  olusturulur
       2) Tüm parametrelerle ve parametresiz constructor larımızı olsuturuyoruz (1 parametreli 1 parametresiz )
       4) Getters ve Setters larımızı olusturuyoruz.
       5) toString() methodumuzu olusturuyoruz.
        */

    private String firstname;
    private String lastname;
    private int totalprice;
    private Boolean depositpaid;
    private String additionalneeds;
    private BookingDatesPojo bookingdates;

    public BookingPojo(String firstname, String lastname, int totalprice, Boolean depositpaid, String additionalneeds, BookingDatesPojo bookingdates) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.additionalneeds = additionalneeds;
        this.bookingdates = bookingdates;
    }

    public BookingPojo() {
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public Boolean getDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(Boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public String getAdditionalneeds() {
        return additionalneeds;
    }

    public void setAdditionalneeds(String additionalneeds) {
        this.additionalneeds = additionalneeds;
    }

    public BookingDatesPojo getBookingdates() {
        return bookingdates;
    }

    public void setBookingdates(BookingDatesPojo bookingdates) {
        this.bookingdates = bookingdates;
    }

    @Override
    public String toString() {
        return "BookingPojo{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", totalprice=" + totalprice +
                ", depositpaid=" + depositpaid +
                ", additionalneeds='" + additionalneeds + '\'' +
                ", bookingdates=" + bookingdates +
                '}';
    }
}
