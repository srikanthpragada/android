package com.st.code;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class SumBoundService extends Service {

	    public class SumBinder extends Binder {
	        SumBoundService getService() {
	            return SumBoundService.this;
	        }
	    }

	    @Override
	    public IBinder onBind(Intent intent) {
	    	Log.i("SumBoundService","onBind()");
			return new SumBinder();
	    }

	    @Override
		public boolean onUnbind(Intent intent) {
	    	Log.i("SumBoundService","onUnBind()");
			return super.onUnbind(intent);
		}

	    public int getSum(int number) {
	       int sum =0;
			for (int i =1; i <= number; i ++)
				 sum+=i;

			return sum;
	    }
}
