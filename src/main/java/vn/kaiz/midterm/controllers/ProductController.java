package vn.kaiz.midterm.controllers;

import vn.kaiz.midterm.models.Product;
import vn.kaiz.midterm.models.ProductDto;
import vn.kaiz.midterm.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    private static final String UPLOADED_FOLDER = "src/main/resources/static/images/";

    @GetMapping
    public String listProducts(Model model,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productService.getAllProducts(pageable);
        model.addAttribute("products", products);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", products.getTotalPages());
        return "products/productList";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("productDto", new ProductDto());
        return "products/createProduct";
    }

    @PostMapping("/create")
    public String createProduct(@ModelAttribute ProductDto productDto,
                                @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDiscountPrice(productDto.getDiscountPrice());
        product.setDescription(productDto.getDescription());
        product.setCategory(productDto.getCategory());
        product.setStatus(productDto.getStatus());

        if (!imageFile.isEmpty()) {
            String fileName = imageFile.getOriginalFilename();
            Path path = Paths.get(UPLOADED_FOLDER + fileName);
            Files.write(path, imageFile.getBytes());
            product.setImageFileName(fileName);
        }

        productService.saveProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/{id}")
    public String viewProduct(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "products/viewProduct";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setDiscountPrice(product.getDiscountPrice());
        productDto.setDescription(product.getDescription());
        productDto.setCategory(product.getCategory());
        productDto.setStatus(product.getStatus());
        model.addAttribute("productDto", productDto);
        return "products/editProduct";
    }

    @PostMapping("/edit")
    public String updateProduct(@ModelAttribute ProductDto productDto) {
        Product product = productService.getProductById(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDiscountPrice(productDto.getDiscountPrice());
        product.setDescription(productDto.getDescription());
        product.setCategory(productDto.getCategory());
        product.setStatus(productDto.getStatus());
        productService.saveProduct(product);
        return "redirect:/products";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }

    @GetMapping("/search")
    public String searchProducts(@RequestParam(value = "name", required = false) String name,
                                 @RequestParam(value = "minPrice", required = false) BigDecimal minPrice,
                                 @RequestParam(value = "maxPrice", required = false) BigDecimal maxPrice,
                                 Model model) {
        List<Product> products;
        if (name != null && !name.isEmpty()) {
            products = productService.searchProductsByName(name);
        } else if (minPrice != null && maxPrice != null) {
            products = productService.searchProductsByPriceRange(minPrice, maxPrice);
        } else {
            products = productService.getAllProducts(PageRequest.of(0, 10)).getContent(); // Default to a page with 10 products if no criteria
        }
        model.addAttribute("products", products);
        return "products/productList";
    }
}
