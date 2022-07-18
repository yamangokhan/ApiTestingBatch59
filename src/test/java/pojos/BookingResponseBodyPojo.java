package pojos;

public class BookingResponseBodyPojo {
     /*
    1) Tüm keyler için Private veriable'lar  olusturulur
    2) Tüm parametrelerle ve parametresiz constructor larımızı olsuturuyoruz (1 parametreli 1 parametresiz )
    4) Getters ve Setters larımızı olusturuyoruz.
    5) toString() methodumuzu olusturuyoruz.
     */

    private int bookingid;
    private BookingPojo booking;

    public BookingResponseBodyPojo(int bookingid, BookingPojo booking) {
        this.bookingid = bookingid;
        this.booking = booking;
    }

    public BookingResponseBodyPojo() {
    }

    public int getBookingid() {
        return bookingid;
    }

    public void setBookingid(int bookingid) {
        this.bookingid = bookingid;
    }

    public BookingPojo getBooking() {
        return booking;
    }

    public void setBooking(BookingPojo booking) {
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "BookingResponseBodyPojo{" +
                "bookingid=" + bookingid +
                ", booking=" + booking +
                '}';
    }
}
