import java.util.*;

public class Project
{
    // Initialise class fields
    private String province;
    private String beneficiary;
    private String beneficiaryNum;
    private String assetClass;
    private String name;
    private String stage;
    private Location location;

    // Parameter constructor
    public Project(String pProvince, String pBeneficiary, String pBeneficiaryNum, String pAssetClass, String pName, String pStage, Location pLocation)
    {
        province = pProvince;
        beneficiary = pBeneficiary;
        beneficiaryNum = pBeneficiaryNum;
        assetClass = pAssetClass;
        name = pName;
        stage = pStage;
        location = pLocation;
    }

    //Copy constructor
    public Project(Project pProject)
    {
        province = pProject.getProvince();
        beneficiary = pProject.getBeneficiary();
        beneficiaryNum = pProject.getBeneficiaryNum();
        assetClass = pProject.getAssetClass();
        name = pProject.getName();
        stage = pProject.getStage();
        location = pProject.getLocation();
    }
 
    // Accessors
    public String getProvince()
    {
        return province;
    }

    public String getBeneficiary()
    {
        return beneficiary;
    }

    public String getBeneficiaryNum()
    {
        return beneficiaryNum;
    }

    public String getAssetClass()
    {
        return assetClass;
    }

    public String getName()
    {
        return name;
    }

    public String getStage()
    {
        return stage;
    }

    public Location getLocation()
    {
        return location;
    }

    // Mutators
    public void setProvince(String pProvince) 
    {
        if (isValidString(pProvince)) 
        {
            province = pProvince;
        } 
        else 
        {
            System.out.println("PInvalid entry detected! Province cannot be null.");
        }
    }

    public void setBeneficiary(String pBeneficiary) 
    {
        if (isValidString(pBeneficiary)) 
        {
            beneficiary = pBeneficiary;
        } 
        else 
        {
            System.out.println("Invalid entry detected! Beneficiary cannot be null.");
        }
    }

    public void setBeneficiaryNum(String pBeneficiaryNum) 
    {
        if (isValidBeneficiaryNum(pBeneficiaryNum)) 
        {
            beneficiaryNum = pBeneficiaryNum;
        } 
        else 
        {
            System.out.println("Invalid entry detected! BeneficiaryNum must be between 1 and 9999 (inclusive).");
        }
    }

    public void setAssetClass(String pAssetClass) 
    {
        if (isValidString(pAssetClass)) 
        {
            assetClass = pAssetClass;
        } 
        else 
        {
            System.out.println("Invalid entry detected! AssetClass cannot be null.");
        }
    }

    public void setName(String pName) 
    {
        if (isValidString(pName)) 
        {
            name = pName;
        } else 
        {
            System.out.println("Invalid entry detected! Name cannot be null.");
        }
    }

    public void setStage(String pStage) 
    {
        if (isValidStage(pStage)) 
        {
            stage = pStage;
        } 
        else 
        {
            System.out.println("Invalid entry detected! Stage must be 'Ongoing' or 'Completed'.");
        }
    }
    
    public void setLocation(Location pLocation)
    {
        location = pLocation;
    }

    // Validation methods
    private boolean isValidString(String str) 
    {
        return str != null;
    }

    private boolean isValidBeneficiaryNum(String num) 
    {
        try 
        {
            int beneficiaryNum = Integer.parseInt(num);
            return beneficiaryNum >= 1 && beneficiaryNum <= 9999;
        } 
        catch (NumberFormatException e) 
        {
            return false;
        }
    }

    private boolean isValidStage(String stage) 
    {
        return isValidString(stage) && (stage.equalsIgnoreCase("Ongoing") || stage.equalsIgnoreCase("Completed"));
    }

    // ToString method
    public String toString()
    {
        String projectsString;
        projectsString = getProvince() + ", " + getBeneficiary() + ", " + getBeneficiaryNum() + ", " + getAssetClass() + ", " + getName() + ", " + getStage() + ", " + getLocation();
        return projectsString;
    }

    // Equals check method
    public boolean equals(Project pProject) 
    {
        return getProvince().equals(pProject.getProvince()) && getBeneficiary().equals(pProject.getBeneficiary()) && getBeneficiaryNum().equals(pProject.getBeneficiaryNum())
            && getAssetClass().equals(pProject.getAssetClass()) && getName().equals(pProject.getName()) && getStage().equals(pProject.getStage())
            && getLocation().equals(pProject.getLocation());
    }
}
