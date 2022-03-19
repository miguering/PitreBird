/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author Migue
 */
public class Pajaro {
    
    public Rectangle pajaro;
    private Texture pajaroImage;
    private Texture pajaroVolando;
    private Texture pajaroMuerto;
    private Texture pajaroCayendo;
    private float velocidad;
    private float aceleracion;
    private boolean game_over;
    
    private final static float VELOCIDAD_CONST = 190;
    private final static float ACELERACION_CONST = -500;

    public Pajaro(Rectangle pajaro, Texture pajaroVolando, float velocidad, float aceleracion) {
        this.pajaro = pajaro;
        this.pajaroVolando = pajaroVolando;
        this.velocidad = velocidad;
        this.aceleracion = aceleracion;
        
    }

    public Texture getPajaroVolando() {
        return pajaroVolando;
    }

    public void setPajaroVolando(Texture pajaroVolando) {
        this.pajaroVolando = pajaroVolando;
    }

    public Texture getPajaroCayendo() {
        return pajaroCayendo;
    }

    public void setPajaroCayendo(Texture pajaroCayendo) {
        this.pajaroCayendo = pajaroCayendo;
    }

    public Texture getPajaroImage() {
        return pajaroImage;
    }

    public void gameOver(boolean gameover){
        this.game_over = gameover;
    }
    public boolean isGameOver(){
        return this.game_over;
    }
    public void setPajaroImage(Texture pajaroImage) {
        this.pajaroImage = pajaroImage;
    }
    
    public void generarPajaroImage(){
        if(this.game_over){
            this.setPajaroImage(this.getPajaroMuerto());
        }else{
        
            if(this.getVelocidad() > 0){
                this.setPajaroImage(this.getPajaroVolando());
            }else if(this.getVelocidad() < 0){
                this.setPajaroImage(this.getPajaroCayendo());
            }
        }
    }
    
    public Pajaro(Rectangle pajaro) {
        this.pajaro = pajaro;
        this.velocidad = 10;
        this.aceleracion = ACELERACION_CONST;
    }
    
    public Pajaro(){
        this.pajaro = new Rectangle();
        this.pajaro.width = 64;
        this.pajaro.height = 54;
        this.velocidad = VELOCIDAD_CONST;
        this.aceleracion = ACELERACION_CONST;
        this.setX(35);
        this.setY((480 / 2 - 64 / 2) + 122);
        this.setPajaroVolando(new Texture(Gdx.files.internal("pajaro_up.png")));
        this.setPajaroCayendo(new Texture(Gdx.files.internal("pajaro_fall1.png")));
        this.pajaroMuerto = new Texture(Gdx.files.internal("pajaro_dead.png"));
        this.game_over = false;
        
    }

    public void saltar(){
        
        //e = v * t

        this.setY(this.getY() + (this.getVelocidad() * Gdx.graphics.getDeltaTime()));
        
        this.setVelocidad(VELOCIDAD_CONST);
        
    }
    
    public void caer(){

        this.setVelocidad(this.getVelocidad() + (this.getAceleracion() * Gdx.graphics.getDeltaTime()));
        this.setY(this.getY() + (getVelocidad() * Gdx.graphics.getDeltaTime()));         
    }
    
    
    public void setY(float pos){
        this.pajaro.y = pos;
    }

    public void setX(float pos){
        this.pajaro.x = pos;
    }
    
    public float getX(){
        return this.pajaro.x;
    }
    
    public float getY(){
        return this.pajaro.y;
    }
    
    public Rectangle getPajaro() {
        return pajaro;
    }

    public void setPajaro(Rectangle pajaro) {
        this.pajaro = pajaro;
    }

    public float getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(float velocidad) {
        this.velocidad = velocidad;
    }

    public float getAceleracion() {
        return aceleracion;
    }

    public void setAceleracion(float aceleracion) {
        this.aceleracion = aceleracion;
    }

    public Texture getPajaroMuerto() {
        return pajaroMuerto;
    }

    public void setPajaroMuerto(Texture pajaroMuerto) {
        this.pajaroMuerto = pajaroMuerto;
    }
    
    

        
}
