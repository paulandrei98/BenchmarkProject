package userInterface;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.OperatingSystem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

public class ComputerSpecifications {

	private String vendor;
	private String model;
	private String virtualCpus;
	private String ram;
	private String description;
	private String architecture;
	private String userName;

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}


	public String getVirtualCpus() {
		return virtualCpus;
	}

	public void setVirtualCpus(String virtualCpus) {
		this.virtualCpus = virtualCpus;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getArchitecture() {
		return architecture;
	}

	public void setArchitecture(String architecture) {
		this.architecture = architecture;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public static ComputerSpecifications getComputerInfo() {
		ComputerSpecifications res = new ComputerSpecifications();
		Sigar sigar = new Sigar();
		OperatingSystem sys = OperatingSystem.getInstance();
		res.setDescription(System.getProperty("os.name"));
		res.setArchitecture(sys.getArch());
		res.setUserName(System.getProperty("user.name"));

		try {
			CpuInfo cpuInfo = sigar.getCpuInfoList()[0];
			res.setVendor(cpuInfo.getVendor());
			res.setModel(cpuInfo.getModel());
			res.setVirtualCpus(cpuInfo.getTotalCores() + "");
			res.setRam(sigar.getMem().getRam() + " MB");
		} catch (SigarException e) {
			System.err.println("Problem loading CPUInfo " + e.getStackTrace());
		}

		return res;
	}
	
	public String getSpecifications() {
		return "<html>"+"Vendor: "+vendor+
				"<br>"+"Model: "+model+
				"<br>"+"Ram: "+ram+
				"<br>"+"VirtualCPUs: "+virtualCpus+
				"<br>"+"Description: "+description+
				"<br>"+"Architecture: "+architecture+
				"<br>"+"User name: "+userName+"</html>";
	
	}
}
