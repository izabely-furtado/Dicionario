/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inteiro.string.quantias;

import taddic.*;

/**
 *
 * @author 20121bsi0040
 */
public class HashEngineIntStr extends HashEngine{
    
    @Override
    public int hashCode(Object k){
        int inteiro = (int) Math.sqrt(k.hashCode()*k.hashCode());
        return inteiro;
    }
}
