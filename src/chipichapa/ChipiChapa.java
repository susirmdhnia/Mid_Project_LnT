package chipichapa;


import java.awt.geom.Arc2D.Double;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;


public class ChipiChapa {
	Scanner scan = new Scanner(System.in);
	Random rand = new Random();
	ArrayList<Karyawan> arrKaryawan = new ArrayList<>();
	private Map<String, Integer>hitungKaryawan = new HashMap<>();
	ArrayList<Double>gajiList = new ArrayList<>();
	ArrayList<String>kodeBonus = new ArrayList<>();
	
	
	public void bubbleSort() {
		for(int i = 0; i < arrKaryawan.size(); i++) {
			for(int j = 0; j < arrKaryawan.size() - 1; j++) {
				
				String first = arrKaryawan.get(j).namaKaryawan;
				String second = arrKaryawan.get(j + 1).namaKaryawan;
				if(first.compareTo(second) > 0) {
					Karyawan temp = arrKaryawan.get(j);
					arrKaryawan.set(j, arrKaryawan.get(j + 1));
					arrKaryawan.set(j + 1, temp);
				}
			}
		}
	}
	
	
	public String randID() {
		char firstChar = (char) ('A' + rand.nextInt(26));
		char secondChar = (char) ('A' + rand.nextInt(26));
		String randNumber = String.format("%04d", rand.nextInt(10000));
		
		String result = "" + firstChar + secondChar +"-" + randNumber;
		return result;
	}

	
	private void mainMenu(){
		System.out.println("================================================");
		System.out.println("Welcome to ChipiChapa Employee Management System");
		System.out.println("================================================");
		System.out.println("1. Insert Employee Data");
		System.out.println("2. View Employee Data");
		System.out.println("3. Update Employee Data");
		System.out.println("4. Delete Employee Data");
		System.out.println("5. Exit");
		System.out.print("Enter your choice >> ");
	}
	
		
	/**
	 * 
	 */
	private void insertData() {
		
		String kodeKaryawan = " ";
		kodeKaryawan = randID();
	
		
		String namaKaryawan = " ";
		try {
			System.out.print("Input employee name [>= 3]: ");
			namaKaryawan = scan.next();
			if(namaKaryawan.length() < 3) {
				throw new Exception("Employee name must be at least 3 alphabetic characters!!!");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		
		String gender = " ";
		try {
			System.out.print("Input employee gender [Laki-Laki | Perempuan] (Case Sensitive): ");
			gender = scan.next();
			if(!gender.equals("Laki-Laki") && !gender.equals("Perempuan")) {
				throw new Exception("Invalid gender input!!!");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		
		String jabatan = "";
		double gaji = 0;
		try {
			System.out.print("Input employee positition [Manager | Supervisor | Admin] (Case Sensitive): ");
			jabatan = scan.next();
			if(jabatan.equals("Manager")) {
				gaji = 8000000;
			}else if(jabatan.equals("Supervisor")) {
				gaji = 6000000;
			}else if(jabatan.equals("Admin")) {
				gaji = 4000000;
			}else {
				throw new Exception("Employee positition must be Manager, Supervisor, or Admin!!!");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
			
		
		int count = hitungKaryawan.getOrDefault(jabatan, 0);
		hitungKaryawan.put(jabatan, count + 3);
		double totalGaji = gaji;

			
		if(count % 3 == 0 && count >=3) {
			double bonusKaryawan = 0;
			if(jabatan.equals("Manager")) {
				bonusKaryawan = 0.1;
			}else if(jabatan.equals("Supervisor")) {
				bonusKaryawan = 0.075;
			}else if(jabatan.equals("Admin")) {
				bonusKaryawan = 0.05;
			}
			
			double bonus = gaji*bonusKaryawan;
			totalGaji += bonus;
			kodeBonus.add(kodeKaryawan);
			
			System.out.println("Bonus sebesar " + (bonus * 100) + "% telah diberikan kepada karyawan dengan id " + kodeKaryawan + ", " + kodeKaryawan + ", " + "dan" + kodeKaryawan);
		}
		
		gajiList.add(totalGaji);
		System.out.println("Successfully added new employee with id " + kodeKaryawan);
		
		
		Karyawan kar = new Karyawan(kodeKaryawan, namaKaryawan, gender, jabatan, gaji);
		arrKaryawan.add(kar);	


	}


	private void viewData() {
		if(arrKaryawan.isEmpty()) {
			System.err.println("There is no employee data!!!");
		}else {
			bubbleSort();
			for (int i = 0; i < arrKaryawan.size(); i++) {
				Karyawan kar = arrKaryawan.get(i);
				System.out.println("================================================");
				System.out.println("No: " + (i+1));
				System.out.println("Employee Code: " + kar.kodeKaryawan);
				System.out.println("Employee Name: " + kar.namaKaryawan);
				System.out.println("Gender: " + kar.gender);
				System.out.println("Positition: " + kar.jabatan);
				System.out.println("Salary: " + "Rp" + kar.gaji);
			}
		}
	}
	
	
	private void updateData() {
		if(arrKaryawan.isEmpty()) {
			System.err.println("There is no employee data!!!");
		}else {
			viewData();
			try {
				System.out.print("Input the number from the list of employee data that you want to update: ");
				int number = scan.nextInt(); scan.nextLine();
				int index = number - 1;
				
				if(index < 0 || index >= arrKaryawan.size()) {
					throw new Exception("Invalid number!!! Please input the number on the list");
				}
				
			Karyawan kar = arrKaryawan.get(index);
			
			System.out.print("Input employee name [>= 3]: ");
			String namaKaryawan = scan.nextLine();
			if(!(namaKaryawan.length() < 3)) {
				if (!namaKaryawan.equals("0")) {
			        kar.namaKaryawan = namaKaryawan;
			}
			}
			
			System.out.print("Input employee gender [Laki-Laki | Perempuan] (Case Sensitive): ");
			String gender = scan.nextLine();
			if(!gender.equals("Laki-Laki") && !gender.equals("Perempuan")) {
				if(!gender.equals("0")) {
					kar.gender = gender;
			}
			}
			
			System.out.print("Input employee positition [Manager | Supervisor | Admin] (Case Sensitive): ");
			String jabatan = scan.nextLine();
			if(!jabatan.equals("Manager") || !jabatan.equals("Supervisor") || !jabatan.equals("Admin")) {
				if(!jabatan.equals("0")) {
					kar.jabatan = jabatan;
			}
			}
				
			System.out.print("Input employee's new salary: ");
			double newGaji = scan.nextDouble(); scan.nextLine();
			if (newGaji != 0) {
		        kar.gaji = newGaji;
		    }
			
			System.out.println("Successfully updated the employee data :)");
			
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}
	
	
	private void deleteData() {
		if(arrKaryawan.isEmpty()) {
			System.err.println("There is no employee data!!!");
		}else {
			viewData();
			
				try {
					System.out.print("Input the number from the list of employee data that you want to delete: ");
					int number = scan.nextInt(); scan.nextLine();
					int index = number - 1;
					
					if(index < 0 || index >= arrKaryawan.size()) {
						throw new Exception("Invalid number!!! Please input the number on the list");
					}
					arrKaryawan.remove(index);
					System.out.println("Successfully deleted the employee data :)");
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
		}
	}
	

	
	public ChipiChapa() {
		boolean running = true;
		while(running) {
			try {
				mainMenu();
				int choose = scan.nextInt();
				if (choose == 1) {
					insertData();
				}else if(choose == 2) {
					viewData();
				}else if(choose == 3) {
					updateData();
				}else if(choose == 4) {
					deleteData();
				}else if(choose == 5) {
					System.out.println("Thank you for using ChipiChapa Employee Management System :)");
					running = false;
				}else {
					throw new Exception("Invalid Choice!!! Please enter a number between 1-5");
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}	
		}


	public static void main(String[] args) {
		new ChipiChapa();
	}

}
