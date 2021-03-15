package android.com.mareu.di;

import android.com.mareu.model.Meeting;
import android.com.mareu.service.MeetingApiService;
import android.com.mareu.service.MeetingRepository;

public class Di {
    /**
     * Dependency injector to get instance of services
     */


        private static MeetingApiService service = new MeetingRepository();

        /**
         * Get an instance on @{@link MeetingApiService}
         * @return
         */
        public static MeetingApiService getMeetingApiService() {
            return service;
        }

        /**
         * Get always a new instance on @{@link MeetingApiService}. Useful for tests, so we ensure the context is clean.
         * @return
         */
        public static MeetingApiService getNewInstanceApiService() {
            return new MeetingRepository();
        }
}

