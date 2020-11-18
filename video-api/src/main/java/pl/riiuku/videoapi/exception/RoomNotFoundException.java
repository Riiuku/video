package pl.riiuku.videoapi.exception;

public class RoomNotFoundException extends RuntimeException {
    public RoomNotFoundException() {
        super();
    }

    public RoomNotFoundException(String s) {
        super(s);
    }

    public RoomNotFoundException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public RoomNotFoundException(Throwable throwable) {
        super(throwable);
    }

    protected RoomNotFoundException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
