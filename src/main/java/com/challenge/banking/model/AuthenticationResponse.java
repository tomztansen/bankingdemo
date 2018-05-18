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
public class AuthenticationResponse extends ModelBase {

   private static final long serialVersionUID = -6624726180748515507L;
  private int type;

  public AuthenticationResponse()
  {
  }

  public AuthenticationResponse(int type)
  {
    setType(type);
  }

  public int getType() {
    return this.type;
  }

  public void setType(int type) {
    this.type = type;
  }

}
