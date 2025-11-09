package emailapp;

public class EmailApp {
    static void main(String[] args) {
        Email em1 = new Email("diroj" ,"khanal");
        em1.setAlternateEmail("33@gmail.com");
        System.out.println(em1.getAlternateEmail());
        System.out.println(em1.showInfo());

    }
}
