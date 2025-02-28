# ext-thor

`ext-thor` 是一个通过扩展 MyBatis Generator 的插件机制生成代码的项目。它旨在提高开发效率，简化代码生成过程。

## 特性

1. **支持 Lombok**：自动生成 getter、setter 和其他常用方法，减少样板代码。
2. **集成 MyBatis Dynamic SQL**：提供动态 SQL 构建能力，增强查询灵活性。
3. **支持 Swagger 注解**：自动生成 API 文档，方便接口测试和使用。
4. **支持 Spring Interface Driven Controllers 模式**：简化控制器的开发，提升代码可维护性。
5. **扩展生成 Controller 和 Service 代码**：自动生成控制器和服务层代码，减少手动编写的工作量。
6. **错误处理**：提供统一的错误处理机制样本代码，提升代码的健壮性。
7. **支持 Java SPI 扩展机制**：允许用户通过 SPI 机制扩展功能。

## 安装

要开始使用 `ext-thor`，请按照以下步骤进行安装：

1. 克隆仓库：
   ```bash
   git clone https://github.com/frudder/ext-thor.git

2. 导入至项目本地项目
