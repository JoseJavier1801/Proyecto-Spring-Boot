package com.example.demo.controllers;

import com.example.demo.Services.*;
import com.example.demo.model.Caja;
import com.example.demo.model.Productos;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
public class ControllerPrueba {


    private final ServicesProductos servicesProductos;


    private final ServicesCaja serviceCaja;

    public ControllerPrueba(ServicesProductos servicesProductos, ServicesCaja serviceCaja) {
        this.servicesProductos = servicesProductos;
        this.serviceCaja = serviceCaja;
    }

    @GetMapping("/")
    public String menu(Model model){
        return "greeting";
    }
    
    @GetMapping("/productos")
    public String showproducts(Model model) {
        List<Productos> productos= servicesProductos.getAllProductos();
        model.addAttribute("list",productos);
        if(productos == null){
            System.out.println("No hay Productos");
        }
        return "productList";
    }

    @GetMapping("/caja")
    public String showboxes(Model model) {
        List<Caja> caja= serviceCaja.getAllBoxes();
        model.addAttribute("list",caja);
        if(caja == null){
            System.out.println("No hay cajas");
        }
        return "boxList";
    }
    @GetMapping("/productos/nuevo")
    public String addProduct(Model model) {
        model.addAttribute("Productos",new Productos());//se crea un producto en blanco
        return "newProduct";
    }

    @PostMapping("/productos/add")
    public RedirectView savebox(@ModelAttribute("Productos") Productos producto,Model model){
        servicesProductos.save(producto);
        return new RedirectView("/productos");
    }

    @GetMapping("/caja/nuevo")
    public String addbox(Model model) {
        model.addAttribute("Caja",new Caja());//se crea una caja en blanco
        return "newBox";
    }

    @PostMapping("/caja/add")
    public RedirectView savebox(@ModelAttribute("caja") Caja caja,Model model){
        serviceCaja.save(caja);
        return new RedirectView("/caja");
    }

    @GetMapping("/productos/edit/{id}")
    public String editProducts(@PathVariable int id, Model model){
        Optional<Productos> aux = servicesProductos.findById(id);
        Productos product= aux.orElseThrow(() ->
                new RuntimeException("El producto no existe")
        );
        model.addAttribute("Productos", product);
        return ("modify");
    }

    @PostMapping("/productos/edit/{id}")
    public Object saveProducts(@ModelAttribute("producto") Productos producto, @PathVariable int id, Model model) {
        producto.setId(id);
        servicesProductos.save(producto);
        return new RedirectView("/productos");
    }

    @GetMapping("/caja/edit/{id}")
    public String editbox(@PathVariable int id, Model model){
        Optional<Caja> aux = serviceCaja.findById(id);
        Caja box= aux.orElseThrow(() ->
                new RuntimeException("La caja no existe")
        );
        model.addAttribute("Caja", box);
        return ("modifyBox");
    }

    @PostMapping("/caja/edit/{id}")
    public Object saveBox(@ModelAttribute("caja") Caja caja, @PathVariable int id, Model model) {
        caja.setId(id);
        serviceCaja.save(caja);
        return new RedirectView("/caja");
    }

    @GetMapping("/caja/delete/{id}")
    public RedirectView deleteBox(@ModelAttribute("id") int id) {
        serviceCaja.delete(id);
        return new RedirectView("/caja");
    }

    @GetMapping("/productos/delete/{id}")
    public RedirectView deleteProducts(@ModelAttribute("id") int id) {
        servicesProductos.delete(id);
        return new RedirectView("/productos");
    }
    @GetMapping("/caja/{id}/add-p")
    public String addProductsToBox(@PathVariable int id, Model model) {
        Optional<Caja> optionalCaja = serviceCaja.findById(id);

        if (optionalCaja.isPresent()) {
            Caja caja = optionalCaja.get();
            List<Productos> productos = servicesProductos.getAllProductos();
            model.addAttribute("cajaId", id);
            model.addAttribute("productos", productos);
            model.addAttribute("caja", caja);
            return "addtobox";
        } else {
            throw new RuntimeException("La caja no existe");
        }
    }

    @PostMapping("/caja/{id}/insert-products")
    public RedirectView addProductsToBox(@PathVariable int id, @RequestParam("productosSeleccionados") List<Integer> productosSeleccionados) {
        Optional<Caja> optionalCaja = serviceCaja.findById(id);

        if (optionalCaja.isPresent()) {
            Caja caja = optionalCaja.get();
            List<Productos> productos = servicesProductos.getProductosByID(productosSeleccionados);

            for (Productos producto : productos) {
                caja.getProductos().add(producto);
                producto.getCajas().add(caja);
            }

            serviceCaja.save(caja);
            servicesProductos.saveAll(productos);
        } else {
            throw new RuntimeException("La caja no existe");
        }

        return new RedirectView("/caja");
    }

    @GetMapping("/caja/{id}/productos")
    public String showProductsInBox(@PathVariable int id, Model model) {
        Optional<Caja> optionalCaja = serviceCaja.findById(id);

        if (optionalCaja.isPresent()) {
            Caja caja = optionalCaja.get();
            List<Productos> productos = caja.getProductos();
            model.addAttribute("caja", caja);
            model.addAttribute("productos", productos);
            return "boxProducts";
        } else {
            throw new RuntimeException("La caja no existe");
        }
    }

}