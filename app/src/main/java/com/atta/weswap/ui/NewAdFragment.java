package com.atta.weswap.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.atta.weswap.R;
import com.atta.weswap.model.Ad;
import com.atta.weswap.model.Area;
import com.atta.weswap.model.Brand;
import com.atta.weswap.model.Category;
import com.atta.weswap.model.Condition;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class NewAdFragment extends Fragment implements NewAdContract.View, View.OnClickListener, AdapterView.OnItemSelectedListener {


    NewAdPresenter newAdPresenter;

    AlertDialog.Builder builder;

    String[] categories, subcategories;

    String type;

    AlertDialog alert;

    Button addBtn;

    ProgressBar progressBar;

    TextInputEditText titleEditText, categoryEditText, phoneEditText, emailEditText, brandEditText,
            warrantyEditText, operatorEditText, genderEditText, conditionEditText, typeEditText,
            descriptionEditText, locationEditText, swapCategoryEditText;

    TextInputLayout brandInputLayout, warrantyInputLayout, operatorInputLayout, genderInputLayout,
            typeInputLayout;

    Spinner conditionSpinner, typeSpinner, brandSpinner, warrantySpinner, operatorSpinner, genderSpinner, areasSpinner;

    ArrayAdapter<String> conditionAdapter, typeAdapter, warrantyAdapter, operatorAdapter, genderAdapter,
            areasAdapter, brandAdapter;

    Category category, swapCategory;

    Subcategory subcategory, swapSubcategory;

    String categoryName, swapCategoryName;

    ImageView addImage;

    List<Bitmap> imagesBitmap;
    List<String> imagesString, imagesName;
    ArrayList<String> warranty;
    Map<String, String> imagesNames, images;
    ArrayList<Area> areas;
    ArrayList<Category> categoriesArray;
    ArrayList<Subcategory> subcategoriesArray;
    ArrayList<Condition> conditions;
    ArrayList<Brand> brands;
    private RecyclerView recyclerView;

    View root;

    private String phone, title, description;
    private int userId, categoryId, subcategoryId, conditionId, brandId, areaId, swapCategoryId, swapSubcategoryId;
    private String warrantyValue;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_new_ad, container, false);

        initiateViews();


        imagesBitmap = new ArrayList<>();
        imagesName = new ArrayList<>();
        imagesString = new ArrayList<>();

        imagesNames = new HashMap<String, String>();
        images = new HashMap<String, String>();


        newAdPresenter = new NewAdPresenter(this, getContext());

        newAdPresenter.getCategories();

        return root;
    }


    private void initiateViews() {
        titleEditText = root.findViewById(R.id.title_et);
        emailEditText = root.findViewById(R.id.email_et);
        emailEditText.setText(SessionManager.getInstance(getContext()).getEmail());
        phoneEditText = root.findViewById(R.id.phone_et);
        phoneEditText.setText(SessionManager.getInstance(getContext()).getUserPhone());
        descriptionEditText = root.findViewById(R.id.description_et);

        brandInputLayout = root.findViewById(R.id.textInputLayout_brand);
        warrantyInputLayout = root.findViewById(R.id.textInputLayout_warranty);
        operatorInputLayout = root.findViewById(R.id.textInputLayout_operator);
        genderInputLayout = root.findViewById(R.id.textInputLayout_gender);
        typeInputLayout = root.findViewById(R.id.textInputLayout_type);

        typeSpinner = root.findViewById(R.id.type_spinner);
        conditionSpinner = root.findViewById(R.id.condition_spinner);
        operatorSpinner = root.findViewById(R.id.operator_spinner);
        genderSpinner = root.findViewById(R.id.gender_spinner);
        brandSpinner = root.findViewById(R.id.brand_spinner);
        warrantySpinner = root.findViewById(R.id.warranty_spinner);
        areasSpinner = root.findViewById(R.id.location_spinner);

        progressBar = root.findViewById(R.id.progress_bar);

        categoryEditText = root.findViewById(R.id.cat_et);
        categoryEditText.setOnClickListener(this);

        swapCategoryEditText = root.findViewById(R.id.swap_cat_et);
        swapCategoryEditText.setOnClickListener(this);

        addImage = root.findViewById(R.id.imageView2);
        addImage.setOnClickListener(this);


        addBtn = root.findViewById(R.id.add_btn);
        addBtn.setOnClickListener(this);

        recyclerView = root.findViewById(R.id.images_recycler);
    }


    @Override
    public void showMessage(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
    }

    public void showCategoriesDialog(final ArrayList<Category> categoriesArray) {

        this.categoriesArray = categoriesArray;

        categories = getCategoryName(categoriesArray);

        builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Main categories");
        builder.setItems(categories, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                // Do something with the selection
                category = categoriesArray.get(item);
                categoryId = category.getId();
                newAdPresenter.getSubCategories(categoryId);
            }
        });
        alert = builder.create();
        alert.show();

    }

    @Override
    public void showSubcategoriesDialog(final ArrayList<Subcategory> subcategoriesArray) {

        this.subcategoriesArray = subcategoriesArray;
        subcategories = getSubcategoryName(subcategoriesArray);

        builder.setItems(subcategories, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                // Do something with the selection
                subcategory = subcategoriesArray.get(item);
                subcategoryId = subcategory.getId();
                categoryName = category.getCategory() + "  > " + subcategory.getSubcategory();

                categoryEditText.setText(categoryName);


                newAdPresenter.getAreas();

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

                showSwapCategoriesDialog();
            }
        });

        alert = builder.create();
        alert.show();
    }

    public void showSwapCategoriesDialog() {

        categories = getCategoryName(categoriesArray);

        builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Swap with categories");
        builder.setItems(categories, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                // Do something with the selection
                swapCategory = categoriesArray.get(item);
                swapCategoryId = swapCategory.getId();
                newAdPresenter.getSwapSubCategories(swapCategoryId);
            }
        });
        alert = builder.create();
        alert.show();

    }


    @Override
    public void showSwapSubcategoriesDialog(final ArrayList<Subcategory> subcategoriesArray) {

        subcategories = getSubcategoryName(subcategoriesArray);

        builder.setItems(subcategories, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                // Do something with the selection
                swapSubcategory = subcategoriesArray.get(item);
                swapSubcategoryId = swapSubcategory.getId();
                swapCategoryName = swapCategory.getCategory() + "  > " + swapSubcategory.getSubcategory();

                swapCategoryEditText.setText(swapCategoryName);

            }
        });

        alert = builder.create();
        alert.show();
    }

    @Override
    public void setAreas(ArrayList<Area> areas) {

        this.areas = areas;
        ArrayList<String> areaNames = getAreaNames(areas);

        areasAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item, areaNames);
        areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        areasSpinner.setAdapter(areasAdapter);
        areasSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void setConditions(ArrayList<Condition> conditions) {

        this.conditions = conditions;
        ArrayList<String> conditionNames = getConditionNames(conditions);

        conditionAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item, conditionNames);
        conditionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        conditionSpinner.setAdapter(conditionAdapter);
        conditionSpinner.setOnItemSelectedListener(this);


    }

    @Override
    public void setBrands(ArrayList<Brand> brands) {

        this.brands = brands;
        ArrayList<String> brandNames = getBrandNames(brands);

        brandAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item, brandNames);
        brandAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        brandSpinner.setAdapter(brandAdapter);
        brandSpinner.setOnItemSelectedListener(this);


    }


    @Override
    public void setWarranty() {

        warranty = new ArrayList<>();
        warranty.add("Yes");
        warranty.add("No");

        warrantyAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item, warranty);
        warrantyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        warrantySpinner.setAdapter(warrantyAdapter);
        warrantySpinner.setOnItemSelectedListener(this);


    }


    @Override
    public void addAd() {

        if (!validate()){
            return;
        }


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.getDefault());

        String creationTime = sdf.format(new Date());
        phone = SessionManager.getInstance(getContext()).getUserPhone();
        userId = SessionManager.getInstance(getContext()).getUserId();
        description = descriptionEditText.getText().toString().trim();

        Ad ad = new Ad(userId, categoryId,subcategoryId, conditionId, brandId, title, creationTime,
                description, phone, images, imagesNames, swapCategoryId, swapSubcategoryId, areaId);

        progressBar.setVisibility(View.VISIBLE);
        newAdPresenter.createAd(ad);

    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void navigateToMain() {
        Navigation.findNavController(getActivity(), R.id.nav_host_fragment)
                .navigate(NewAdFragmentDirections.actionNewAdFragmentToNavHome());
    }

    private boolean validate() {
        boolean valid = true;


        if (category == null) {

            showMessage("Select the Category");
            valid = false;
        }

        title = titleEditText.getText().toString().trim();
        if (title == null) {

            showMessage("Select the Title");
            valid = false;
        }

        return valid;
    }

    private ArrayList<String> getAreaNames(ArrayList<Area> areas) {

        ArrayList<String> areaNames = new ArrayList<>();

        for (Area area : areas){
            areaNames.add(area.getAreaName());
        }
        return areaNames;
    }

    private ArrayList<String> getConditionNames(ArrayList<Condition> conditions) {

        ArrayList<String> conditionNames = new ArrayList<>();

        for (Condition condition : conditions){
            conditionNames.add(condition.getConditionName());
        }
        return conditionNames;
    }

    private ArrayList<String> getBrandNames(ArrayList<Brand> brands) {

        ArrayList<String> brandNames = new ArrayList<>();

        for (Brand brand : brands){
            brandNames.add(brand.getBrand());
        }
        return brandNames;
    }



    public static String[] getCategoryName(ArrayList<Category> arr){

        // declaration and initialise String Array
        String[] str = new String[arr.size()];

        // ArrayList to Array Conversion
        for (int j = 0; j < arr.size(); j++) {

            // Assign each value to String array
            str[j] = arr.get(j).getCategory();
        }

        return str;
    }

    public static String[] getSubcategoryName(ArrayList<Subcategory> arr){

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
        }else if (view == swapCategoryEditText){
            showSwapCategoriesDialog();
        }else if (view == addImage){
            getImages();
        }else if (view == addBtn){
            addAd();
        }
    }


    public void getImages(){
        ImagePicker.create(this)
                .returnMode(ReturnMode.NONE) //set whether pick and / or camera action should return immediate result or not.
                .folderMode(true) // folder mode (false by default)
                .toolbarFolderTitle("Select folder") // folder selection title
                .toolbarImageTitle("Tap to select") // image selection title
                .toolbarArrowColor(Color.BLACK) // Toolbar 'up' arrow color
                .multi() // multi mode (default mode)
                .limit(8) // max images can be selected (99 by default)
                .showCamera(true) // show camera or not (true by default)
                .enableLog(false) // disabling log
                .start();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

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
                ImagesAdapter myAdapter = new ImagesAdapter(getContext(), imagesBitmap);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        if (adapterView.getId() == conditionSpinner.getId()){
            conditionId = conditions.get(i).getId();
        }else if (adapterView.getId() == brandSpinner.getId()){
            brandId = brands.get(i).getId();
        }else if (adapterView.getId() == areasSpinner.getId()){
            areaId = areas.get(i).getId();
        }else if (adapterView.getId() == warrantySpinner.getId()){
            warrantyValue = warranty.get(i+1);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}