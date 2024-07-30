package com.proteccion.apirest_proteccion.Utils;

import java.time.LocalTime;
import java.util.List;

public class FibonacciResponse {

    private LocalTime executionTime;
    private List<Long> fibonacciSeries;

    public FibonacciResponse(LocalTime executionTime, List<Long> fibonacciSeries) {
        this.executionTime = executionTime;
        this.fibonacciSeries = fibonacciSeries;
    }

    public LocalTime getExecutionTime() {
        return executionTime;
    }

    public List<Long> getFibonacciSeries() {
        return fibonacciSeries;
    }
}
