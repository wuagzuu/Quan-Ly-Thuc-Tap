package spring.dev.quanlithuctap.util;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import spring.dev.quanlithuctap.entity.Major;
import spring.dev.quanlithuctap.entity.Role;
import spring.dev.quanlithuctap.entity.User;
import spring.dev.quanlithuctap.repository.MajorRepository;
import spring.dev.quanlithuctap.repository.RoleRepository;
import spring.dev.quanlithuctap.repository.UserRepository;

import java.util.Date;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {
    private RoleRepository roleRepository;
    private UserRepository userRepository;

    private MajorRepository majorRepository;

    public DataInitializer(RoleRepository roleRepository, UserRepository userRepository, MajorRepository majorRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.majorRepository = majorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        createRoleData();
        createUserData();
        createMajorData();
    }

    private void createMajorData() {
        if(majorRepository.count() == 0){
            Major major = new Major();
            major.setName("Công nghệ phần mềm");
            major.setDescription("Application, Website, SEO, ...");
            Major major1 = new Major();
            major1.setName("Khoa học dữ liệu");
            major1.setDescription("Xử lý dữ liệu, Phân tích dữ liệu, ...");
            Major major2 = new Major();
            major2.setName("Trí tuệ nhân tạo");
            major2.setDescription("Machine Learning, AI, ...");
            Major major3 = new Major();
            major3.setName("An ninh mạng");
            major3.setDescription("Tấn công mạng, bảo vệ mạng, ...");
            majorRepository.saveAll(List.of(major1, major2, major3, major));
        }
    }

    private void createRoleData(){
        if (roleRepository.count() == 0) {
            Role adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            Role userRole = new Role();
            userRole.setName("ROLE_STUDENT");
            Role teacherRole = new Role();
            teacherRole.setName("ROLE_TEACHER");
            roleRepository.save(adminRole);
            roleRepository.save(userRole);
            roleRepository.save(teacherRole);

            System.out.println("Roles initialized successfully!");
        } else {
            System.out.println("Roles already exists. No initialization needed.");
        }
    }

    private void createUserData(){

        if (userRepository.count() == 0) {
            Role userRole = roleRepository.findByName("USER");
            Role adminRole = roleRepository.findByName("ADMIN");
            Role teacherRole = roleRepository.findByName("TEACHER");

            User user = new User();
            user.setRole(userRole);
            user.setName("Nguyễn Văn A");
            user.setUsername("102100100");
            user.setPassword("123");
            user.setAddress("TPHCM");
            user.setEmail("nguyenvana@yopmail.com");
            user.setActive(true);

            User admin = new User();
            admin.setRole(adminRole);
            admin.setName("Admin");
            admin.setUsername("admin");
            admin.setPassword("123");
            admin.setAddress("TPHCM");
            admin.setEmail("admin@yopmail.com");
            admin.setActive(true);

            User teacher = new User();
            teacher.setRole(teacherRole);
            teacher.setName("Teacher 1");
            teacher.setUsername("teacher1");
            teacher.setPassword("123");
            teacher.setAddress("TPHCM");
            teacher.setEmail("teacher1@yopmail.com");
            teacher.setActive(true);

            userRepository.save(user);
            userRepository.save(teacher);
            userRepository.save(admin);

            System.out.println("Users initialized successfully!");
        } else {
            System.out.println("Users already exists. No initialization needed.");
        }
    }
}
