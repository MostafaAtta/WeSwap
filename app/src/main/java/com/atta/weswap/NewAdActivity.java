package com.atta.weswap;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.atta.weswap.model.Category;
import com.atta.weswap.model.ImagesAdapter;
import com.atta.weswap.model.SessionManager;
import com.atta.weswap.model.Subcategory;
import com.atta.weswap.presenters.NewAdContract;
import com.atta.weswap.presenters.NewAdPresenter;
import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.features.ReturnMode;
import com.esafirm.imagepicker.model.Image;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NewAdActivity extends AppCompatActivity implements NewAdContract.View, View.OnClickListener {

    NewAdPresenter newAdPresenter;

    AlertDialog.Builder builder;

    String[] categories, subcategories;

    String type;

    AlertDialog alert;

    TextInputEditText titleEditText, categoryEditText, phoneEditText, emailEditText, brandEditText,
            warrantyEditText, operatorEditText, genderEditText, conditionEditText, typeEditText,
            descriptionEditText, locationEditText;

    TextInputLayout brandInputLayout, warrantyInputLayout, operatorInputLayout, genderInputLayout,
            typeInputLayout;

    Spinner conditionSpinner, typeSpinner, brandSpinner, warrantySpinner, operatorSpinner, genderSpinner;

    Category category;

    Subcategory subcategory;

    String categoryName;

    ImageView addImage;

    List<Bitmap> imagesBitmap;
    List<String> imagesString, imagesName;

    Map<String, String> imagesNames, images;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_ad);

        initiateViews();


        imagesBitmap = new ArrayList<>();
        imagesName = new ArrayList<>();
        imagesString = new ArrayList<>();

        imagesNames = new HashMap<String, String>();
        images = new HashMap<String, String>();


        newAdPresenter = new NewAdPresenter(this, this);

        newAdPresenter.getCategories();

    }

    private void initiateViews() {
        titleEditText = findViewById(R.id.title_et);
        emailEditText = findViewById(R.id.email_et);
        emailEditText.setText(SessionManager.getInstance(this).getEmail());
        phoneEditText = findViewById(R.id.phone_et);
        phoneEditText.setText(SessionManager.getInstance(this).getUserPhone());

        brandInputLayout = findViewById(R.id.textInputLayout_brand);
        warrantyInputLayout = findViewById(R.id.textInputLayout_warranty);
        operatorInputLayout = findViewById(R.id.textInputLayout_operator);
        genderInputLayout = findViewById(R.id.textInputLayout_gender);
        typeInputLayout = findViewById(R.id.textInputLayout_type);

        typeSpinner = findViewById(R.id.type_spinner);
        conditionSpinner = findViewById(R.id.condition_spinner);
        operatorSpinner = findViewById(R.id.operator_spinner);
        genderSpinner = findViewById(R.id.gender_spinner);
        brandSpinner = findViewById(R.id.brand_spinner);
        warrantySpinner = findViewById(R.id.warranty_spinner);

        categoryEditText = findViewById(R.id.cat_et);
        categoryEditText.setOnClickListener(this);

        addImage = findViewById(R.id.imageView2);
        addImage.setOnClickListener(this);

        recyclerView = findViewById(R.id.images_recycler);
    }


    @Override
    public void showMessage(String error) {

    }

    public void showCategoriesDialog(final ArrayList<Category> categoriesArray) {

        categories = getCategoryName(categoriesArray);

        builder = new AlertDialog.Builder(this);
        builder.setTitle("Main categories");
        builder.setItems(categories, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                // Do something with the selection
                category = categoriesArray.get(item);

                newAdPresenter.getSubCategories(category.getId());
            }
        });
        alert = builder.create();
        alert.show();

    }

    @Override
    public void showSubcategoriesDialog(final ArrayList<Subcategory> subcategoriesArray) {

        subcategories = getSubcategoryName(subcategoriesArray);

        builder.setItems(subcategories, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                // Do something with the selection
                subcategory = subcategoriesArray.get(item);

                categoryName = category.getCategory() + "  > " + subcategory.getSubcategory();

                categoryEditText.setText(categoryName);

                switch (subcategory.getSubcategory()){
                    case "Mobile Phones":
                    case "Tablets":
                    case "TV, Audio, & Video":
                    case "Computers & Accessories":
                    case "Cameras":
                    case "Home Appliances":

                        brandInputLayout.setVisibility(View.VISIBLE);
                        warrantyInputLayout.setVisibility(View.VISIBLE);
                        brandSpinner.setVisibility(View.VISIBLE);
                        warrantySpinner.setVisibility(View.VISIBLE);
                        break;
                    case "Mobile Numbers":
                        operatorInputLayout.setVisibility(View.VISIBLE);
                        operatorSpinner.setVisibility(View.VISIBLE);
                        break;
                    case "Birds - Pigeons":
                    case "Cats":
                    case "Dogs":
                    case "Other Pets & Animals":
                        genderInputLayout.setVisibility(View.VISIBLE);
                        genderSpinner.setVisibility(View.VISIBLE);
                        typeInputLayout.setVisibility(View.GONE);
                        typeSpinner.setVisibility(View.GONE);
                        type = "none";
                        break;
                    case  "Other Baby Items":
                    case  "Other Items":
                        typeInputLayout.setVisibility(View.GONE);
                        typeSpinner.setVisibility(View.GONE);
                        type = "none";
                        break;
                }
            }
        });

        alert = builder.create();
        alert.show();
    }

    public static String[] getCategoryName(ArrayList<Category> arr)
    {

        // declaration and initialise String Array
        String[] str = new String[arr.size()];

        // ArrayList to Array Conversion
        for (int j = 0; j < arr.size(); j++) {

            // Assign each value to String array
            str[j] = arr.get(j).getCategory();
        }

        return str;
    }

    public static String[] getSubcategoryName(ArrayList<Subcategory> arr)
    {

        // declaration and initialise String Array
        String[] str = new String[arr.size()];

        // ArrayList to Array Conversion
        for (int j = 0; j < arr.size(); j++) {

            // Assign each value to String array
            str[j] = arr.get(j).getSubcategory();
        }

        return str;
    }

    @Override
    public void onClick(View view) {

        if (view == categoryEditText){
            genderInputLayout.setVisibility(View.GONE);
            brandInputLayout.setVisibility(View.GONE);
            warrantyInputLayout.setVisibility(View.GONE);
            operatorInputLayout.setVisibility(View.GONE);
            newAdPresenter.getCategories();
        }else if (view == addImage){
            getImages2();
        }
    }


    public void getImages2(){
        ImagePicker.create(this)
                .returnMode(ReturnMode.NONE) // set whether pick and / or camera action should return immediate result or not.
                .folderMode(true) // folder mode (false by default)
                .toolbarFolderTitle("Folder") // folder selection title
                .toolbarImageTitle("Tap to select") // image selection title
                .toolbarArrowColor(Color.BLACK) // Toolbar 'up' arrow color
                .multi() // multi mode (default mode)
                .limit(8) // max images can be selected (99 by default)
                .showCamera(true) // show camera or not (true by default)
                .enableLog(false) // disabling log
                .start();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            // Get a list of picked images
            List<Image> imageList = ImagePicker.getImages(data);

            if (!imageList.isEmpty()|| imageList != null){


                if (imagesBitmap.size() == 8){
                    imagesBitmap.clear();
                    imagesName.clear();
                    images.clear();
                    imagesNames.clear();
                }
                for (int i = 0; i < imageList.size(); i++){
                    imagesBitmap.add(getBitmapFromPath(imageList.get(i).getPath()));
                    imagesName.add(imageList.get(i).getName());
                    getStringImage(getBitmapFromPath(imageList.get(i).getPath()));


                    images.put("images[" + i + "]", getStringImage(getBitmapFromPath(imageList.get(i).getPath())));

                    imagesNames.put("imagesName[" + i + "]", imageList.get(i).getName());
                }

                if (imagesBitmap.size() == 8){
                    addImage.setVisibility(View.GONE);
                }
                ImagesAdapter myAdapter = new ImagesAdapter(this, imagesBitmap);
                recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                recyclerView.setAdapter(myAdapter);
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    private Bitmap getBitmapFromPath(String filePath) {

        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeFile(filePath,bmOptions);
        return bitmap;

    }

    public String getStringImage(Bitmap bm){
        ByteArrayOutputStream ba = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG,100,ba);
        byte[] imageByte = ba.toByteArray();
        String encode = Base64.encodeToString(imageByte,Base64.DEFAULT);
        return encode;
    }

}
