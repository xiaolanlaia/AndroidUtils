package com.wjf.moduledesignpattern.actionType.StragetyPattern.learn;

import android.util.Log;

public class BusStrategy implements CalculateStrategy {
    @Override
    public int calculatePrice(int km) {
        int extraTotal = km - 10;
        Log.d("__price-bus-1","extraTotal = "+extraTotal);
        int extraFactor = extraTotal / 5;
        Log.d("__price-bus-2","extraFactor = "+extraFactor);

        int fraction = extraTotal % 5;
        Log.d("__price-bus-3","fraction = "+fraction);

        int price = 1 + extraFactor * 1;
        Log.d("__price-bus-4","price = "+price);

//        FutureTask<Integer> futureTask = new FutureTask<Integer>(new Callable<Integer>() {
//            @Override
//            public Integer call() throws Exception {
//
//                try {
//                    Log.d("__price-bus-5","sleep pre ");
//
//                    Thread.sleep(5000);
//                    Log.d("__price-bus-6","sleep after ");
//
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//                return 0;
//            }
//        });
//        Log.d("__price-bus-7","1");
//
//        Thread thread = new Thread(futureTask);
//        thread.start();
//        Log.d("__price-bus-8","1");
//
//        try {
//            Log.d("__price-bus-9","1");
//
//            futureTask.get(1000, TimeUnit.SECONDS);
//            Log.d("__price-bus-10","1");
//
//        }catch (Exception e){
//            Log.d("__price-bus-11",""+e.getMessage());
//
//            e.printStackTrace();
//        }
        Log.d("__price-bus-12","1");

        return fraction > 0 ? ++price : price;
    }
}
