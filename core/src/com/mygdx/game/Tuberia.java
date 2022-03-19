/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

/**
 *
 * @author Migue
 */
public class Tuberia {
    public Rectangle tuberia_superior;
    public Rectangle tuberia_inferior;
    private Texture tuberiaImage1;
    private Texture tuberiaImage2;
    private float velocidad;
    public static float VELOCIDAD = 200;

    public Tuberia() {
        
        this.tuberia_superior = new Rectangle();
        this.tuberia_inferior = new Rectangle();
        
        this.tuberiaImage1 = new Texture(Gdx.files.internal("tuberia1.png"));
        
        this.tuberiaImage2 = new Texture(Gdx.files.internal("tuberia2.png"));
        
        this.tuberia_superior.width = 100;
        this.tuberia_superior.height = 400;
        
        this.tuberia_inferior.width = 100;
        this.tuberia_inferior.height = 400;
        
        this.velocidad = VELOCIDAD;
        
    }

    public float getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(float velocidad) {
        this.velocidad = velocidad;
    }
        
    public Rectangle getTuberia_superior() {
        return tuberia_superior;
    }

    public Rectangle getTuberia_inferior() {
        return tuberia_inferior;
    }

    public void setTuberia_inferior(Rectangle tuberia_inferior) {
        this.tuberia_inferior = tuberia_inferior;
    }
    
    

    public void setTuberia_superior(Rectangle tuberia_superior) {
        this.tuberia_superior = tuberia_superior;
    }
    
    public void setY_superior(float pos){
        this.tuberia_superior.y = pos;
    }

    public void setX_superior(float pos){
        this.tuberia_superior.x = pos;
    }
    
    public float getX_superior(){
        return this.tuberia_superior.x;
    }
    
    public float getY_superior(){
        return this.tuberia_superior.y;
    }
    
    public void setY_inferior(float pos){
        this.tuberia_inferior.y = pos;
    }

    public void setX_inferior(float pos){
        this.tuberia_inferior.x = pos;
    }
    
    public float getX_inferior(){
        return this.tuberia_inferior.x;
    }
    
    public float getY_inferior(){
        return this.tuberia_inferior.y;
    }

    public Texture getTuberiaImage1() {
        return tuberiaImage1;
    }
    
    public Texture getTuberiaImage2() {
        return tuberiaImage2;
    }

    public void setTuberiaImage1(Texture tuberiaImage) {
        this.tuberiaImage1 = tuberiaImage;
    }
    
    public void setTuberiaImage2(Texture tuberiaImage) {
        this.tuberiaImage2 = tuberiaImage;
    }
    
    
}
