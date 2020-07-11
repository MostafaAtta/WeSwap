package com.atta.weswap.presenters;

import com.atta.weswap.model.Ad;
import com.atta.weswap.model.Area;
import com.atta.weswap.model.Category;
import com.atta.weswap.model.Condition;
import com.atta.weswap.model.Subcategory;

import java.util.ArrayList;

public interface NewAdContract {

    interface View{

        void showMessage(String error);

        void showCategoriesDialog(final ArrayList<Category> categoriesArray);

        void showSubcategoriesDialog(final ArrayList<Subcategory> subcategories);

        void setAreas(ArrayList<Area> areas);

        void setConditions(ArrayList<Condition> conditions);

        void addAd();

    }

    interface Presenter{

        void getCategories();

        void getSubCategories(int catId);

        void createAd(Ad ad);

        void getAreas();
    }
}
