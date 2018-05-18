/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.challenge.banking.model;

/**
 *
 * @author Tommy Tansen <Stronger21us@yahoo.com>
 */

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import java.io.Serializable;
public class ModelBase implements Serializable {

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }

}
