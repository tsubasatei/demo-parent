package com.xt.juc.interview;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.atomic.AtomicReference;

@Data
@AllArgsConstructor
class User {
    String username;
    int age;
}

public class AtomicReferenceDemo {

    public static void main(String[] args) {
        User z3 = new User("z3", 18);
        User li4 = new User("li4", 20);
        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(z3);
        System.out.println(atomicReference.compareAndSet(z3, li4) + "\t " + atomicReference.get().toString()); // true	 User(username=li4, age=20)
        System.out.println(atomicReference.compareAndSet(z3, li4) + "\t " + atomicReference.get().toString()); // false	 User(username=li4, age=20)
    }
}
