package ru.practicum.mediasoft;

public class ClassWithUniqueIdentity {

    private static long idCounter;

    private final long uniqueId;

    public ClassWithUniqueIdentity() {
        uniqueId = ++idCounter;
    }

    public long getUniqueId() {
        return uniqueId;
    }

    public static void main(String[] args) {
        ClassWithUniqueIdentity classWithUniqueIdentity = new ClassWithUniqueIdentity();
        ClassWithUniqueIdentity classWithUniqueIdentity1 = new ClassWithUniqueIdentity();
        ClassWithUniqueIdentity classWithUniqueIdentity2 = new ClassWithUniqueIdentity();

        System.out.println(classWithUniqueIdentity.getUniqueId());
        System.out.println(classWithUniqueIdentity1.getUniqueId());
        System.out.println(classWithUniqueIdentity2.getUniqueId());
    }
}
