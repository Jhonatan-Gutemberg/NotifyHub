package notificacoes.domain;

public class Recipient {

    public String address;

    Recipient(String address) {
        this.address = address;
    }

    private void setAddress(String address) {
        if (address == null || address.isEmpty()) {
            return;
        }
        this.address = address;
    }

    private String getAddress() {
        return this.address;
    }
}
