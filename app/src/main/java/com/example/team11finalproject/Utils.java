package com.example.team11finalproject;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;

public class Utils
{
    private static final String ALL_RECIPES_KEY = "all_recipes";
    private static final String ALL_Ingredients_KEY = "all_ingredients";
    private static final String PANTRY_INGREDIENTS_KEY = "pantry_ingredients";

    private static Utils instance;      //create a singleton so only one database exists
    //way of persisting data, basically a file that saves user pref like theme, but can be used for simple data types
    private SharedPreferences sharedPreferences;

    private static final String TAG = "MyActivity";     //Tag so we can see the recipes in the Logcat info


    private Utils(Context context)
    {
        //create the database
        sharedPreferences = context.getSharedPreferences("alternate_db", Context.MODE_PRIVATE);

        initData();
        SharedPreferences.Editor editor = sharedPreferences.edit();
    }

    private void initData()
    {
        //TODO: add data
        //ArrayList of Recipes which have a list of ingredients and a name. Can be changed to add more details such as calories or duration
        ArrayList<Recipe> recipes = new ArrayList<>();
        ArrayList<String> NonVeg = new ArrayList<>(Arrays.asList("Non Veg"));
        ArrayList<String> NoRes = new ArrayList<>(Arrays.asList(""));
        ArrayList<String> forAll = new ArrayList<>(Arrays.asList("Vegetarian", "No Shellfish", "No Peanuts", "Lactose Intolerant", "N"));


        recipes.add(new Recipe(new ArrayList<String>(Arrays.asList("noodles", "marinara sauce")), "Spaghetti", NoRes));
        recipes.add(new Recipe(new ArrayList<String>(Arrays.asList("chicken", "eggs", "rice")), "Fried Rice", NonVeg));
        recipes.add(new Recipe(new ArrayList<String>(Arrays.asList("milk", "eggs")), "Swedish Pancakes", NoRes));
        recipes.add(new Recipe(new ArrayList<String>(Arrays.asList("rice", "chicken")), "Orange Chicken", NonVeg));
        recipes.add(new Recipe(new ArrayList<String>(Arrays.asList("sausage", "noodles")), "Lasagna", NonVeg));
        recipes.add(new Recipe(new ArrayList<String>(Arrays.asList("chicken", "ranch", "peppers", "butter")), "Mississippi Chicken", NonVeg ));
        recipes.add(new Recipe(new ArrayList<String>(Arrays.asList("cheese", "egg", "sausage", "marinara sauce", "flatbread")), "Lasagna Flatbread",NonVeg));
        recipes.add(new Recipe(new ArrayList<String>(Arrays.asList("macaroni", "butter", "milk", "cheese")), "Mac and Cheese", NoRes));
        recipes.add(new Recipe(new ArrayList<String>(Arrays.asList("noodles", "olive oil", "garlic", "red pepper")), "Spaghetti Aglio e Olio", NoRes));
        recipes.add(new Recipe(new ArrayList<String>(Arrays.asList("beef", "broccoli", "peppers", "carrot", "garlic", "rice")), "Beef Stir-Fry Rice", NonVeg));
        recipes.add(new Recipe(new ArrayList<String>(Arrays.asList("rice", "garlic", "peppers", "chicken", "broccoli", "carrot")), "Chicken Stir-Fry Rice",NonVeg));
        recipes.add(new Recipe(new ArrayList<String>(Arrays.asList("beef", "egg", "cheese", "bun")), "Hamburger",NonVeg));
        recipes.add(new Recipe(new ArrayList<String>(Arrays.asList("sausage", "bun", "ketchup")), "Hotdog",NonVeg));
        recipes.add(new Recipe(new ArrayList<String>(Arrays.asList("noodles", "beef", "garlic")), "Beef Stroganoff",NonVeg));
        recipes.add(new Recipe(new ArrayList<String>(Arrays.asList("bread", "butter", "cheese")), "Grilled Cheese Sandwich",NoRes));
        recipes.add(new Recipe(new ArrayList<String>(Arrays.asList("beef", " bun", "ketchup", "garlic", "peppers" )), "Sloppy Joe's",NonVeg));
/*        recipes.add(new Recipe(new ArrayList<String>(Arrays.asList("")), "",res));
        recipes.add(new Recipe(new ArrayList<String>(Arrays.asList("")), "",res));
        recipes.add(new Recipe(new ArrayList<String>(Arrays.asList("")), "",res));
        recipes.add(new Recipe(new ArrayList<String>(Arrays.asList("")), "",res));
        recipes.add(new Recipe(new ArrayList<String>(Arrays.asList("")), "",res));*/



        //array list of ingredients, needs URL for the images from internet. Will take longer
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient(1,  "chicken", "lb", "https://www.news-medical.net/image.axd?picture=2019%2F5%2FBy_Tatiana_Volgutov.jpg"));
        ingredients.add(new Ingredient(2, "rice","lb", "https://m.economictimes.com/thumb/msid-94280172,width-1200,height-900,resizemode-4,imgsize-143428/price-rise-high-shipment-led-to-export-restrictions-on-rice-food-secretary.jpg"));
        ingredients.add(new Ingredient(3, "eggs", "#", "https://cdn1.sph.harvard.edu/wp-content/uploads/sites/30/2012/09/FPG_06-EggsCarton_FeaturedImage.jpg"));
        ingredients.add(new Ingredient(4, "noodles", "lb","https://upload.wikimedia.org/wikipedia/commons/thumb/7/73/Mama_instant_noodle_block.jpg/1200px-Mama_instant_noodle_block.jpg"));
        ingredients.add(new Ingredient(5, "marinara sauce", "ml", "https://images.heb.com/is/image/HEBGrocery/000250585"));
        ingredients.add(new Ingredient(6, "milk","ml", "https://www.meijer.com/content/dam/meijer/product/0004/12/5010/20/0004125010200_2_A1C1_1200.png"));
        ingredients.add(new Ingredient(7, "beef", "lb", "https://cdn.britannica.com/68/143268-050-917048EA/Beef-loin.jpg"));
        ingredients.add(new Ingredient(8, "peppers", "#", "https://i2.wp.com/chilipeppermadness.com/wp-content/uploads/2019/08/Bell-Peppers.jpg"));
        ingredients.add(new Ingredient(9, "butter", "tb", "https://media.npr.org/assets/img/2017/02/23/butter1_custom-340e5902a868b66b8f11aca60a5b2eed334971eb-s1100-c50.jpg"));
        ingredients.add(new Ingredient(10, "sausage", "lb", "https://dearbornbrand.com/wp-content/uploads/2013/03/MildItalianSausage.jpg"));
        ingredients.add(new Ingredient(11, "cheese", "oz", "https://www.greatlakescheese.com/Data/Sites/24/images/homepage/hp-hero-quality.jpg"));
        ingredients.add(new Ingredient(12, "flatbread", "#", "https://easyweeknightrecipes.com/wp-content/uploads/2020/04/Flatbread-4.jpg"));
        ingredients.add(new Ingredient(13, "macaroni", "lb", "https://www.muellerspasta.com/wp-content/uploads/2016/08/Mac-and-Cheese-HR-for-Web.jpg"));
        ingredients.add(new Ingredient(14, "olive oil", "ml", "https://m.media-amazon.com/images/I/61uWT5siLmL.jpg"));
        ingredients.add(new Ingredient(15, "garlic", "#", "https://hips.hearstapps.com/hmg-prod/images/f2d17b1a-5e72-4eda-8bf1-56d68aa86115-1656799717.jpeg"));
        ingredients.add(new Ingredient(16, "ranch", "oz", "https://i5.walmartimages.com/asr/1bd75296-46fb-4934-849b-5151280f8b78.7926e6b5e517907cc8fa30fc71681f34.jpeg"));
        ingredients.add(new Ingredient(17, "broccoli", "oz", "https://post.greatist.com/wp-content/uploads/sites/2/2021/07/383439-grt-Broccoli-Benefits-732x549-thumbnail-732x549.jpg"));
        ingredients.add(new Ingredient(18, "carrot", "#", "https://images.immediate.co.uk/production/volatile/sites/30/2021/11/carrots-953655d.jpg"));
        ingredients.add(new Ingredient(19, "bread", "#", "https://i2.wp.com/lmld.org/wp-content/uploads/2020/05/Homemade-white-bread-7.jpg"));
        ingredients.add(new Ingredient(20, "bun", "#", "https://www.chatelaine.com/wp-content/uploads/2020/06/CHE07_WEB_FD_BLOGGERS_1280x720_6.jpg"));
        ingredients.add(new Ingredient(21,"potatoes", "pounds","https://images.heb.com/is/image/HEBGrocery/000318982"));
        ingredients.add(new Ingredient(22, "ketchup", "ml", "https://i.ebayimg.com/images/g/CKEAAOSwQl1gCsSd/s-l1600.jpg"));
        ingredients.add(new Ingredient(23, "white bread", "#","https://www.freshdirect.com/media/images/product/bakery_1/bak_pid_4651029_z.jpg?lastModify=2015-12-29"));
        ingredients.add(new Ingredient(24, "cheddar cheese", "grams","https://www.wisconsinrivermeats.com/prodimages/cheddar-cheese.jpg"));
        ingredients.add(new Ingredient(25, "american cheese", "#","https://cdn.schwans.com/media/images/products/62172-1-1540.jpg"));
        ingredients.add(new Ingredient(26, "tomatoes", "grams","https://i0.wp.com/images-prod.healthline.com/hlcmsresource/images/AN_images/tomatoes-1296x728-feature.jpg?w=1155&h=1528"));
        ingredients.add(new Ingredient(27, "lettuce", "grams","https://i0.wp.com/post.healthline.com/wp-content/uploads/2020/03/romaine-lettuce-1296x728-body.jpg?w=1155&h=1528"));
        ingredients.add(new Ingredient(28, "ground beef", "pounds","https://i5.walmartimages.com/asr/bd4bb7c1-43a8-4f75-b1be-f3c2d98fff56.39dda363a61de8a41ddd6521b2385d45.jpeg"));
        ingredients.add(new Ingredient(29, "tortillas", "#","https://tmbidigitalassetsazure.blob.core.windows.net/toh/GoogleImagesPostCard/Homemade-Tortillas_EXPS_CIW19_48431_B08_30_7b.jpg"));
        ingredients.add(new Ingredient(30, "onions", "grams","https://i5.walmartimages.com/asr/a17b4251-be9b-4ae3-8d71-1f427930c520.2921cb7c559c4b55d91de9567cbff634.jpeg"));
        //ingredients.add(new Ingredient(, "", "", ""));


        ArrayList<Guest> guests = new ArrayList<>();
        guests.add(new Guest(new ArrayList<>(Arrays.asList("veg")), "Bob"));

        //users pantry/fridge
        ArrayList<Ingredient> pantry = new ArrayList<Ingredient>();
        pantry.add(new Ingredient(1,  "peppers", "#", 10, "https://i2.wp.com/chilipeppermadness.com/wp-content/uploads/2019/08/Bell-Peppers.jpg"));
        pantry.add(new Ingredient(2, "milk","ml", 1000, "https://www.meijer.com/content/dam/meijer/product/0004/12/5010/20/0004125010200_2_A1C1_1200.png"));
        pantry.add(new Ingredient(3, "marinara sauce", "ml", 500, "https://images.heb.com/is/image/HEBGrocery/000250585"));
        pantry.add(new Ingredient(4, "butter", "tb", 5, "https://media.npr.org/assets/img/2017/02/23/butter1_custom-340e5902a868b66b8f11aca60a5b2eed334971eb-s1100-c50.jpg"));
        pantry.add(new Ingredient(5, "ketchup", "ml", 100, "https://i.ebayimg.com/images/g/CKEAAOSwQl1gCsSd/s-l1600.jpg"));

        //this is how we commit changes to the database
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        //putting recipes to a key name in the shared preferences database
        editor.putString(ALL_RECIPES_KEY, gson.toJson(recipes));    //converts the recipe array to stings, called json which can be shared between all languages
        editor.putString(ALL_Ingredients_KEY, gson.toJson(ingredients));
        editor.putString(PANTRY_INGREDIENTS_KEY, gson.toJson(pantry));

        editor.commit();    //changes to persistent is applied immediately, better to use apply in most cases
    }

    public static synchronized Utils getInstance(Context context)   //singleton, sychronized will make it thread safe
    {
        if (null != instance)
        {
            return instance;
        }
        else
        {
            instance = new Utils(context);
            return instance;
        }
    }

    /**
     * returns the user pantry
     * @return users pantry
     */
    public ArrayList<Ingredient> getPantry()
    {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Ingredient>>(){}.getType();   //declaring a data type
        ArrayList<Ingredient> pantry = gson.fromJson(sharedPreferences.getString(PANTRY_INGREDIENTS_KEY, null), type);  //gets the value from the pref, translates it to the type we give
        return pantry;
    }

    /**
     * Takes in the ingredient and adds it to the database
     * @param ingredient
     * @return
     */
    public boolean addIngredient(Ingredient ingredient, int amount)
    {
        ArrayList<Ingredient> pantry = getPantry();
        if(pantry != null)
        {
            for (int i = 0; i < pantry.size(); i++)
            {
                if(ingredient.getName().equals(pantry.get(i).getName()))
                    return false;
            }

            Ingredient addIngredient = new Ingredient(ingredient.getId(), ingredient.getName(), ingredient.getMeasurement(), amount, ingredient.getImageUrl());

            if(pantry.add(addIngredient))      //will return true if it is succesfully added
            {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(PANTRY_INGREDIENTS_KEY);  //removes the original array
                editor.putString(PANTRY_INGREDIENTS_KEY, gson.toJson(pantry));  //add the new array
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean removeIngredient(Ingredient ingredient)
    {
        ArrayList<Ingredient> pantry = getPantry();
        if(pantry != null) {
            for (int i = 0; i < pantry.size(); i++) {
                if (ingredient.getName().equals(pantry.get(i).getName())) {
                    if (pantry.remove(pantry.get(i))) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(PANTRY_INGREDIENTS_KEY);  //removes the original array
                        editor.putString(PANTRY_INGREDIENTS_KEY, gson.toJson(pantry));  //add the new array
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Gets all ingredients in the database
     * @return
     */
    public ArrayList<Ingredient> getAllIngredients()        //changed from static to not static for the shared preferences, idk why
    {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Ingredient>>(){}.getType();   //declaring a data type
        ArrayList<Ingredient> ingredients = gson.fromJson(sharedPreferences.getString(ALL_Ingredients_KEY, null), type);  //gets the value from the pref, translates it to the type we give
        return ingredients;
    }

    /**
     * Gets all the recipes
     * @return
     */
    public ArrayList<Recipe> getAllRecipes()        //changed from static to not static for the shared preferences, idk why
    {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Recipe>>(){}.getType();   //declaring a data type
        ArrayList<Recipe> recipes = gson.fromJson(sharedPreferences.getString(ALL_RECIPES_KEY, null), type);  //gets the value from the pref, translates it to the type we give
        return recipes;
        //return allBooks;
    }

    /**
     * This is the code that iterates through the recipes and returns that valid recipes
     * Still need to implement the restrictions such as Veg/Non Veg or nut allergy etc..
     * Still need to implement the amounts such as tablespoons, 500ml, 1 lb etc..
     * @param pantry
     * @return
     */
    public ArrayList<Recipe> getApplicableRecipes(ArrayList<Ingredient> pantry)
    {
        //gets the recipes from the database
        ArrayList<Recipe> recipes = getAllRecipes();
        ArrayList<Recipe> returnRecipes = new ArrayList<Recipe>();
        for (Recipe r : recipes)            //Loop through every recipe
        {
            //assume the pantry does not have the ingredient for the recipe
            boolean hasItems = false;
            for (int i = 0; i < r.getIngredients().size(); i++)     //loop through the ingredients in the recipe
            {
                hasItems = false;
                for (int j = 0; j < pantry.size(); j++)     //loop through pantry
                {
                    if(r.getIngredients().get(i).equals(pantry.get(j).getName()))     //if the recipe ingredient is present in the pantry
                    {
                        hasItems = true;    //we have the item so we can break this loop and check the next item in the recipe
                        break;
                    }
                }
                //if hasItems is false, we did not have the ingredient so we can break since there is no point checking the rest
                if(hasItems == false)
                    break;
            }

            if(hasItems)
                returnRecipes.add(r);

            //Log.i(TAG, r.getDish());
        }
        return returnRecipes;
    }
}
