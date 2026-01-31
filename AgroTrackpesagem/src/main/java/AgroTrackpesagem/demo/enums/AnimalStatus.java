package AgroTrackpesagem.demo.enums;

public enum AnimalStatus {
    ACTIVE,
    SOLD,
    DECEASED;

    public boolean canTransitionTo(AnimalStatus nextStatus) {
        return this != DECEASED; // Morto não muda mais
    }
}
