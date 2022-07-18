package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingDatesPojo {
    /*
    1) Tüm keyler için Private veriable'lar  olusturulur
    2) Tüm parametrelerle ve parametresiz constructor larımızı olsuturuyoruz (1 parametreli 1 parametresiz )
    4) Getters ve Setters larımızı olusturuyoruz.
    5) toString() methodumuzu olusturuyoruz.
     */

    public BookingDatesPojo() {
    }

    private String checkin;
    private String checkout;

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public BookingDatesPojo(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    @Override
    public String toString() {
        return "BookingDatesPojo{" +
                "checkin='" + checkin + '\'' +
                ", checkout='" + checkout + '\'' +
                '}';
    }
}
