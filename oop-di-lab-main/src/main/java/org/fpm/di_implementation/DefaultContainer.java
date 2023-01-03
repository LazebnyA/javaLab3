package org.fpm.di_implementation;

import org.fpm.di.BinderCashMap;
import org.fpm.di.BinderImplementationsMap;
import org.fpm.di.Container;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Optional;

public class DefaultContainer implements Container {

    /*
    Цей DI контейнер впроваджує залежності через конструктор, тому його можна використовувати для впровадження залежностей
    у классах з final полями.
    Інтанси помічених анотацією Singleton класи будуть поміщатися в binderCashMap, й використовуватися повторно.
    В кеш також поміщаються, bound (забінжені) в відповідній конфігурації об'єкти відповідних класів.
    В binderImplMap поміщаються імплементації відповідних інтерфейсів чи класи що розширяють класи.
     */

    public final BinderImplementationsMap binderImplMap;
    public final BinderCashMap binderCashMap;



    public DefaultContainer(BinderImplementationsMap binderImplMap, BinderCashMap binderCashMap) {
        this.binderImplMap = binderImplMap;
        this.binderCashMap = binderCashMap;
    }


    @Override
    public <T> T getComponent(Class<T> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Class<T> componentClass = clazz;
        if (binderImplMap.getImplementationsMap().containsKey(componentClass)) {
            return getComponent(binderImplMap.getImplementationClass(componentClass));
        }

        if (binderCashMap.getCashMap().containsKey(componentClass) && componentClass.isAnnotationPresent(Singleton.class)) {
            return (T) binderCashMap.getCashMap().get(componentClass);
        }



        Optional<Constructor<?>> constructorOpt = Arrays.stream(componentClass.getConstructors()).filter(constructor -> constructor.isAnnotationPresent(Inject.class)).findFirst();
        // Arrays.stream(componentClass.getConstructors()).filter(constructor -> constructor.isAnnotationPresent(Inject.class)).findFirst();
        // щоб findFirst() в stream-і міг знайти лише один - помічений анотацією Inject - конструктор


        if (constructorOpt.isEmpty()) {
            throw new RuntimeException("Class doesn't have any annotated constructors");
        }

        Constructor<?> constructor = constructorOpt.get();


        if (binderCashMap.getCashMap().containsKey(componentClass)) {

            /*
            Ця умовна конструкція потрібна лише для того, щоб не помічені Singleton анотацією класи, які є в кеші
            були впроваджені згідно з полями прикріплених до них об'єктів. (Тобто, щоб кожного разу створювався новий об'єкт,
            оскільки клас не був помічений анотацією Singleton.)
            Головне, щоб поля в класі були розміщені в тому ж порядку, в якому вони впроваджуються в конструкторі.
             */

            T componentCopyFromCash = (T) binderCashMap.getCashMap().get(componentClass);
            Object[] componentFields = new Object[componentCopyFromCash.getClass().getDeclaredFields().length];

            for (int i = 0; i < componentCopyFromCash.getClass().getDeclaredFields().length; i++) {
                Field field = componentCopyFromCash.getClass().getDeclaredFields()[i];
                field.setAccessible(true);
                Object fieldValue = field.get(componentCopyFromCash);
                componentFields[i] = fieldValue;
            }

            T component = (T) constructor.newInstance(componentFields);

            return component;
        }


        Class<?>[] constructorParametersClasses = constructor.getParameterTypes();

        if (constructorParametersClasses.length == 0) {
            T component = (T) constructor.newInstance();
            return component;
        }

        Object[] constructorParameters = new Object[constructorParametersClasses.length];


        for (int i = 0; i < constructorParameters.length; i++) {
            if (binderImplMap.getImplementationsMap().containsKey(constructorParametersClasses[i]) && binderCashMap.getCashMap().containsKey(binderImplMap.getImplementationClass(constructorParametersClasses[i]))) {
                constructorParameters[i] = binderCashMap.getCashMap().get(binderImplMap.getImplementationClass(constructorParametersClasses[i]));
            }
            else if (binderCashMap.getCashMap().containsKey(constructorParametersClasses[i])){
                constructorParameters[i] = binderCashMap.getCashMap().get(constructorParametersClasses[i]);
            }
            else {
                constructorParameters[i] = getComponent(constructorParametersClasses[i]);
            }

        }

        T component = (T) constructor.newInstance(constructorParameters);

        if (componentClass.isAnnotationPresent(Singleton.class) && !binderCashMap.getCashMap().containsKey(componentClass) ) {
            binderCashMap.getCashMap().put(componentClass, component);
        }

        return component;
    }
}
