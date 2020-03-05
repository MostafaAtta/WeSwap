package com.atta.weswap.presenters;

import com.atta.weswap.model.Category;
import com.atta.weswap.model.Subcategory;

import java.util.ArrayList;

public interface NewAdContract {

    interface View{

        void showMessage(String error);

        void showCategoriesDialog(final ArrayList<Category> categoriesArray);

        void showSubcategoriesDialog(final ArrayList<Subcategory> subcategories);

    }

    interface Presenter{

        void getCategories();

        void getSubCategories(int catId);
    }
}
