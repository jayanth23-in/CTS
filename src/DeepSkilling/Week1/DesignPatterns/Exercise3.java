
package DeepSkilling.Week1.DesignPatterns;

class Computer {
    private  String cpu;
    private  String ram;
    private  String storage;
    private  String gpu;
    private  String powerSupply;

    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
        this.gpu = builder.gpu;
        this.powerSupply = builder.powerSupply;
    }

    public String toString() {
        return "Computer [CPU=" + cpu + ", RAM=" + ram + ", Storage=" + storage +
                ", GPU=" + gpu + ", PowerSupply=" + powerSupply + "]";
    }

    static class Builder {
        private String cpu;
        private String ram;
        private String storage;
        private String gpu;
        private String powerSupply;

        public Builder setCpu(String cpu) {
            this.cpu = cpu;
            return this;
        }

        public Builder setRam(String ram) {
            this.ram = ram;
            return this;
        }

        public Builder setStorage(String storage) {
            this.storage = storage;
            return this;
        }

        public Builder setGpu(String gpu) {
            this.gpu = gpu;
            return this;
        }

        public Builder setPowerSupply(String powerSupply) {
            this.powerSupply = powerSupply;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }
}

public class Exercise3 {
    public static void main(String[] args) {
        Computer officeComputer = new Computer.Builder()
                .setCpu("Intel i5")
                .setRam("8GB")
                .setStorage("512GB SSD")
                .build();

        Computer gamingComputer = new Computer.Builder()
                .setCpu("Intel i9")
                .setRam("32GB")
                .setStorage("2TB SSD")
                .setGpu("RTX 4090")
                .setPowerSupply("850W")
                .build();

        System.out.println(officeComputer);
        System.out.println(gamingComputer);
    }
}