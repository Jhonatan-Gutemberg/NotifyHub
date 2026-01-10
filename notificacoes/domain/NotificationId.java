package notificacoes.domain;

import java.time.LocalDate;
import java.util.Objects;

public class NotificationId {

    Long value;
    LocalDate date;

    private NotificationId(Long value, LocalDate date) {
        this.value = Objects.requireNonNull(value, "Value is not null");
        this.date = Objects.requireNonNull(date, "date is not null");
    }

    public static NotificationId of(Long vale, LocalDate date) {
        return new NotificationId(vale, date);

    }

    private void setValue(Long value) {
        this.value = value;
    }

    private Long getValue() {
        return this.value;
    }

    private void setdate(LocalDate date) {
        this.date = date;
    }

    private LocalDate getdate() {
        return this.date;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NotificationId)) {
            return false;
        }
        NotificationId that = (NotificationId) obj;
        return value.equals(that.value) && date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, date);
    }

    @Override
    public String toString() {
        return value + "-" + date;
    }

}
