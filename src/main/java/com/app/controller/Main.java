package com.app.controller;

import com.app.model.*;
import com.app.repository.*;
import com.app.service.*;

public class Main {
    public static void main(String[] args) {
        FuncionarioService fs = new FuncionarioService();
        if(fs.deletar(50L)) {

        }
    }
}