package com.example.demo.bootstrap;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.repositories.InhousePartRepository;
import com.example.demo.repositories.OutsourcedPartRepository;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.service.OutsourcedPartService;
import com.example.demo.service.OutsourcedPartServiceImpl;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 *
 *
 *
 *
 */
@Component
public class BootStrapData implements CommandLineRunner {

    private final PartRepository partRepository;
    private final ProductRepository productRepository;

    private final OutsourcedPartRepository outsourcedPartRepository;

    public BootStrapData(PartRepository partRepository, ProductRepository productRepository, OutsourcedPartRepository
            outsourcedPartRepository) {
        this.partRepository = partRepository;
        this.productRepository = productRepository;
        this.outsourcedPartRepository=outsourcedPartRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // code used for testing empty inventories
        /*
        outsourcedPartRepository.deleteAll();
        partRepository.deleteAll();
        productRepository.deleteAll();
        */


        // sample inventory to be added on start up if zero inventory detected
        if (partRepository.count() == 0 && productRepository.count() == 0) {
            OutsourcedPart rtx4090 = new OutsourcedPart();
            rtx4090.setName("RTX 4090");
            rtx4090.setPrice(1600.00);
            rtx4090.setInv(5);
            rtx4090.setCompanyName("Nvidia");
            rtx4090.setMinInv(3);
            rtx4090.setMaxInv(10);
            OutsourcedPart rx7900xtx = new OutsourcedPart();
            rx7900xtx.setName("RX 7900XTX");
            rx7900xtx.setPrice(1200.00);
            rx7900xtx.setInv(8);
            rx7900xtx.setMinInv(3);
            rx7900xtx.setMaxInv(15);
            rx7900xtx.setCompanyName("AMD");
            OutsourcedPart evo970 = new OutsourcedPart();
            evo970.setName("EVO 970 SSD 1TB");
            evo970.setPrice(90.00);
            evo970.setInv(70);
            evo970.setMinInv(5);
            evo970.setMaxInv(100);
            evo970.setCompanyName("Samsung");
            OutsourcedPart fractalTorrent = new OutsourcedPart();
            fractalTorrent.setName("Fractal Torrent");
            fractalTorrent.setPrice(230.00);
            fractalTorrent.setInv(15);
            fractalTorrent.setMinInv(5);
            fractalTorrent.setMaxInv(30);
            fractalTorrent.setCompanyName("Fractal");
            OutsourcedPart logiMouse = new OutsourcedPart();
            logiMouse.setName("Logitech G203 Mouse");
            logiMouse.setPrice(23.00);
            logiMouse.setInv(70);
            logiMouse.setMinInv(3);
            logiMouse.setMinInv(500);
            logiMouse.setCompanyName("Logitech");

            outsourcedPartRepository.save(rtx4090);
            outsourcedPartRepository.save(rx7900xtx);
            outsourcedPartRepository.save(evo970);
            outsourcedPartRepository.save(fractalTorrent);
            outsourcedPartRepository.save(logiMouse);

            productRepository.save(new Product("Entry Level PC", 800.00, 23));
            productRepository.save(new Product("Budget Built", 1500.00, 30));
            productRepository.save(new Product("Mid-Range PC", 2000.00, 15));
            productRepository.save(new Product("High End PC", 3000.00, 10));
            productRepository.save(new Product("Enthusiast Built", 4000.00, 5));
        }

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Products: " + productRepository.count());
        System.out.println(productRepository.findAll());
        System.out.println("Number of Parts: " + partRepository.count());
        System.out.println(partRepository.findAll());

    }
}
