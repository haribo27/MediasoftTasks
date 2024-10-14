package ru.practicum.mediasoft;

public class HomeSingleton {

    private HomeSingleton() {
    }

    private static class SingletonHelper {
        private static final HomeSingleton INSTANCE = new HomeSingleton();
    }

    public static HomeSingleton getInstance() {
        return SingletonHelper.INSTANCE;
    }
}
