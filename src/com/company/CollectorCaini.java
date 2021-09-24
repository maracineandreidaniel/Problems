package com.company;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class CollectorCaini implements Collector<Caine,Map<Boolean, List<Caine>>,Map<Boolean,List<Caine>>> {
    @Override
    public Supplier<Map<Boolean, List<Caine>>> supplier() {
        return () -> {
            return new HashMap<Boolean, List<Caine>>() {
                {
                put(true,new ArrayList<>());

                put(false,new ArrayList<>());
            }
            };
        };
    }

    @Override
    public BiConsumer<Map<Boolean, List<Caine>>, Caine> accumulator() {
        return(var acc, var caine) -> {
            acc.get(caine.getVarsta()>7).add(caine);
        };
    }

    @Override
    public BinaryOperator<Map<Boolean, List<Caine>>> combiner() {
        return (var map, var addMap)->{
            map.get(true).addAll(addMap.get(true));
            map.get(false).addAll(addMap.get(false));
            return map;
        };
    }

    @Override
    public Function<Map<Boolean, List<Caine>>, Map<Boolean, List<Caine>>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Set.of(Characteristics.IDENTITY_FINISH,Characteristics.CONCURRENT);
    }
}
