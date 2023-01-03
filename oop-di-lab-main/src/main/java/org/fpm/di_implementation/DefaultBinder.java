package org.fpm.di_implementation;

import org.fpm.di.Binder;
import org.fpm.di.BinderCashMap;
import org.fpm.di.BinderImplementationsMap;

import javax.inject.Inject;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Optional;


public class DefaultBinder implements Binder {

    public final BinderImplementationsMap binderImplMap;
    public final BinderCashMap binderCashMap;


    public DefaultBinder(BinderImplementationsMap binderImplMap, BinderCashMap binderCashMap) {
        this.binderImplMap = binderImplMap;
        this.binderCashMap = binderCashMap;
    }

    @Override
    public <T> void bind(Class<T> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        if (binderImplMap.getImplementationsMap().containsKey(clazz)) {
            bind(binderImplMap.getImplementationClass(clazz));
        }
        else {

            Optional<Constructor<?>> constructorOpt = Arrays.stream(clazz.getConstructors()).filter(constructor -> constructor.isAnnotationPresent(Inject.class)).findFirst();

            if (constructorOpt.isEmpty()) {
                throw new RuntimeException("Class doesn't have any annotated constructors");
            }

            Constructor<T> constructor = (Constructor<T>) constructorOpt.get();
            Class<?>[] constructorParametersClasses = constructor.getParameterTypes();

            if (constructorParametersClasses.length == 0) {
                T component = clazz.getDeclaredConstructor().newInstance();
                binderCashMap.getCashMap().put(clazz, component);
            }

            Object[] constructorParameters = new Object[constructorParametersClasses.length];

            for (int i = 0; i < constructorParameters.length; i++) {

                if (binderImplMap.getImplementationsMap().containsKey(constructorParametersClasses[i])) {
                    Class dependencyImplClass = binderImplMap.getImplementationsMap().get(constructorParametersClasses[i]);
                    constructorParameters[i] = binderCashMap.getCashMap().get(dependencyImplClass);
                }
                else if (!(binderCashMap.getCashMap().containsKey(constructorParametersClasses[i]))){
                    throw new RuntimeException("Dependency hasn't been bound in Configuration, dependency class: " + constructorParametersClasses[i].toString());
                }
                else {
                    constructorParameters[i] = binderCashMap.getCashMap().get(constructorParametersClasses[i]);
                }

            }

            T component = constructor.newInstance(constructorParameters);

            binderCashMap.getCashMap().put(clazz, component);
        }
    }

    @Override
    public <T> void bind(Class<T> clazz, Class<? extends T> implementation) {
        binderImplMap.getImplementationsMap().put(clazz, implementation);
    }

    @Override
    public <T> void bind(Class<T> clazz, T instance) {
        binderCashMap.getCashMap().put(clazz, instance);
    }


}
