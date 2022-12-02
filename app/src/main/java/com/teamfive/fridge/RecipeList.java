package com.teamfive.fridge;

import java.io.Serializable;

public class RecipeList implements Serializable {

    //변수 선언
    String recipe_name, recipe_ingre, recipe_contents, menu_img;


    public RecipeList(){
        super();
    }
    //이거는 그룹을 생성할때 사용하는 부분
    public RecipeList(String recipe_name, String recipe_ingre, String recipe_contents, String menu_img) {
        this.recipe_name = recipe_name;
        this.menu_img = menu_img;
        this.recipe_ingre = recipe_ingre;
        this.recipe_contents = recipe_contents;
    }

    //여기서부터 get,set 함수를 사용하는데 이부분을 통해 값을 가져옴
    public String getRecipe_name() {

        return recipe_name;
    }

    public void setRecipe_name(String recipe_name) {

        this.recipe_name = recipe_name;
    }

    public String getRecipe_ingre() {
        return recipe_ingre;
    }

    public void setRecipe_ingre(String recipe_ingre) {
        this.recipe_ingre = recipe_ingre;
    }

    public String getRecipe_contents() {
        return recipe_contents;
    }

    public void setRecipe_contents(String recipe_contents) {
        this.recipe_contents = recipe_contents;
    }

    public String getMenu_img() {
        return menu_img;
    }

    public void setMenu_img(String menu_img) {
        this.menu_img = menu_img;
    }

    @Override
    public String toString(){
        return "RecipeList{" +
                "recipe_name='" + recipe_name + '\'' +
                ", menu_img='" + menu_img + '\'' +
                ", recipe_ingre='" + recipe_ingre + '\'' +
                ", recipe_contents='" + recipe_contents + '\'' +
                '}';
    }


}

