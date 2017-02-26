package com.crazygeeks.trippleyum.Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class MainListModel {


        public final String userName;
        public final String postContent;
        public final int favState;

        public MainListModel(String userName, String content, int favState) {
            this.userName = userName;
            this.postContent = content;
            this.favState = favState;
        }

        @Override
        public String toString() {
            return postContent;
        }

}
