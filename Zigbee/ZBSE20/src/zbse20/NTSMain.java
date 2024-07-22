/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zbse20;

import Common.XmlSerialize;
import Compiler.CallScriptFiles;
import Compiler.ResultTestcase;
import Object.DemoClass;
import bsh.Interpreter;
import com.server.DisplayConsole;
import com.server.IRMIUserService;
import com.user.DeviceInfo;
import com.user.User;
import java.awt.*;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import javax.swing.text.StyleConstants;
import javax.swing.text.*;
import org.springframework.beans.BeansException;

/**
 *
 * @author CONG HUY
 */
public class NTSMain extends javax.swing.JFrame {

    /**
     * Creates new form NTSMain
     */
    
     
    private javax.swing.tree.DefaultMutableTreeNode treeRoot;
    public String clientHost;    
    public ArrayList<String> HostList = new ArrayList<String>();       
    public ObjectClasses objClas = new  ObjectClasses();
    public LibraryClass library = new LibraryClass(uiConsolleArea);
    public DefaultTableModel functionSetModel;   
    public DefaultTableModel uiTestCaseModel;
    private ArrayList<String> arrayfunList;    
    private int deviceSelectIndex;
    private ArrayList<String> defaultfuncList;
    public IRMIUserService rmiHost;
    public IRMIUserService rmiCoordinator;
    public IRMIUserService rmiClient;
    public String machineName;
    public String machineIp;
    public String keyNumber;
    public boolean IsServerSide;
    public String host;
    public int port = 1099;
    public Library  lib ;
    public User user;
    public ColorRenderer clrender = new ColorRenderer("Status");
    
    
    public String path = System.getProperty("user.dir");    
    private List<DeviceInfo> deviceList;
    private List<List> statusList = new ArrayList<List>();
    private boolean isSkip = false;
    private ConfigureFile configFile = new ConfigureFile();
    public Interpreter inter;
    public ResultTestcase testcaseResult;
    public CallScriptFiles callscript;
    public NTSMain() 
    {
        initComponents(); 
        try
        {  
            if(1==0)
            {
                DemoClass demo = new DemoClass(this);
                demo.convertXMLToObject();
            }
            else 
            {
                testcaseResult = new ResultTestcase();
                inter = new Interpreter();
                inter.set("NTSSE", this);
                inter.set("testcase", testcaseResult);
                callscript = new CallScriptFiles(this);
                lib = new Library();
                try {
                    machineName=InetAddress.getLocalHost().getHostName();
                    machineIp = InetAddress.getLocalHost().getHostAddress();
                } catch (UnknownHostException ex) 
                {
                 uiConsolleAreaPrint(ex.getMessage() +"\n");   
                }        
                uiConsolleAreaPrint("=============================================================\n");
                uiConsolleAreaPrint("=============================================================\n");
                uiConsolleAreaPrint("                                 "+library.getDateTime() +"\n");
                uiConsolleAreaPrint("=============================================================\n\n");
                uiConsolleAreaPrint("Localhost name : "+machineName+"\n");
                uiConsolleAreaPrint("Localhost Address : "+machineIp+"\n");

                uiPanelMain.setVisible(false);         
                SetUpHost();
            }    
        }
        catch(Exception ex){}
    }

    public void PrintCommandConsole(String msg, boolean newLine)
    {
        uiConsolleArea.append(msg + (newLine?"\n":""));
    }
    public void SetLog(boolean status, String msg)
    {
        testcaseResult.status = status;
        testcaseResult.result = msg;
        if(status)
            SetTextConsole(Color.blue, null, "Status = \"PASSED\"", true);
        else 
            SetTextConsole(Color.RED, null, "Status = \"FAILED\"", true);
    }
    public void Print(String msg, boolean newLine)
    {
        SetTextConsole(Color.black, null, msg, newLine);
    }
    public void NTSPrint(String msg, boolean newLine)
    {
        uiConsolleAreaPrint(msg + (newLine?"\n":""));
    }
    public Object convertXMLToObject(Object obj, String xml)
    {
       return  XmlSerialize.convertXMLToObject(obj.getClass(), xml);
    }
    
    public List convertXMLToObjectList(Object obj, String xml)
    {
        return  XmlSerialize.convertXMLToObjectList(obj.getClass(), xml);
    }
    
    public Object convertXMLFileToObject(Object obj, String fileName)
    {
        return XmlSerialize.convertXMLFileToObject(obj.getClass(), fileName);
    }
    public List convertXMLFileToObjectList(Object obj, String fileName)
    {
        return  XmlSerialize.convertXMLFileToObjectList(obj.getClass(), fileName);        
    }
    public void PrintTestConsole(boolean pass, String msg, boolean newLine)
    {
        if(pass)
        {
            SetTextConsole(Color.blue, null, "Received package at "+GetCurrentDateTime()+ ": Status = \"OK\"", true);
            SetTextConsole(Color.blue, null, msg, newLine);
        }
        else             
        {  
            SetTextConsole(Color.red, null, "Received package at "+GetCurrentDateTime()+ ": Status = \"Error\"", true);
            SetTextConsole(Color.red, null, msg, newLine);
        }
    }
    
    private void LoadConfigDevice() {
        uiConsolleAreaPrint("##################################\n");
        uiConsolleAreaPrint("System is loading Devices......\n");
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            
        }
        objClas.ShowAllDevice(uiCombDevice, uiConsolleArea);
        uiConsolleAreaPrint("\n##################################\n");
        uiConsolleAreaPrint("System is loading Function Sets......\n");
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            
        }
        SetFunctionSetModel();            
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            
        }
        objClas.ShowFunctionSet(functionSetModel, uiConsolleArea);
        SetEnableDeviceButton(false);
        uiConsolleAreaPrint("\n##################################\n");
        uiConsolleAreaPrint("System is loading Test Cases......\n");
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            
        }
        SetTestCaseModel();
        // objClas.printAllTestCase(uiConsolleArea);
        uiConsolleAreaPrint("System is loaded sucessful.\n");
        try {
            machineName=InetAddress.getLocalHost().getHostName();
            machineIp = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException ex) 
        {
         uiConsolleAreaPrint(ex.getMessage() +"\n");   
        }        
        LoadConfigFileForFunctionSet(); 
        user = new User();
        user.setName(machineName);
        user.setIp(machineIp);
        if(configFile!=null)
        {
            user.endDevice.deviceInformation.configureNetworkProtocal = configFile.ConfigureNetWorkProtocol;
            user.endDevice.deviceInformation.configureSimulatedDevice = configFile.ConfigurSimulatiedDevice;
            user.endDevice.deviceInformation.configureSystemService = configFile.ConfigureSystemService;
        }
        lib = new Library();
        lib.user = user;
        //Help();
        //ReadConfigFile();
        uiConsolleArea.setCaretPosition(uiConsolleArea.getDocument().getLength());
        
    }
    
    public ConfigureFile getConfigureFile()
    {
        return configFile;
    }
    public void setConfigureFile(ConfigureFile conf)
    {
        configFile = conf;
        if(rmiHost!= null)
            user = rmiHost.GetDevice();
        user.endDevice.deviceInformation.configureSimulatedDevice = configFile.ConfigurSimulatiedDevice;
        user.endDevice.deviceInformation.configureNetworkProtocal = configFile.ConfigureNetWorkProtocol;
        user.endDevice.deviceInformation.configureSystemService = configFile.ConfigureSystemService;
        if(rmiHost!= null)
            rmiHost.SetDevice(user);
    }
    
    private void LoadConfigFileForFunctionSet()
    {        
        configFile =  library.LoadConfigFile("configProfile.xml");
        if(configFile!=null)
        {
            if(!configFile.deviceType.equals("None"))
                uiCombDevice.setSelectedItem(configFile.deviceType);
            for(int i =0; i< uiTableFunctionSet.getModel().getRowCount(); i++)
            {
                for(FunctionSet func: configFile.FunctionSets )
                {
                    if(uiTableFunctionSet.getModel().getValueAt(i, 1).toString().endsWith(func.functionsetName))
                      {
                         if(func.server.equals("1"))
                             uiTableFunctionSet.getModel().setValueAt(true,i, 2);
                         if(func.client.equals("1"))
                             uiTableFunctionSet.getModel().setValueAt(true,i, 3);
                      }
                }
            }
            if(!firstTime)
                ApplyToShowTestCaseList();
            firstTime = false;
        }
        
    }
    private boolean firstTime = true;
     public void Help()
   {
//     Method[] methods =  cmdMethod.getClass().getMethods();
//             for(Method method : methods)
//     {
//       String med = method.getName();       
//        uiConsole.append(method +"\n" +med);
//     }
        uiConsolleAreaPrint("setProperty(String propertyName, Object value)\n");
        uiConsolleAreaPrint("getProperty(String propertyName) \n");
   }
    public void ReadConfigFile()
   {
       
       String file = path +"/config.txt";
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(file));
            try {
                while (in.ready()) {
                String s = in.readLine();
                if(s.indexOf("#")!=0 && s.indexOf(":")>0)                    
                {
                    String[] param = s.split(":");
                    lib.setProperties(param[0].trim(), param[1].trim());
                            
                }               
                }
            } catch (IOException ex) {
               uiConsolleAreaPrint(ex.getMessage() +"\n");
            }
            try {
                in.close();
            } catch (IOException ex) {
                 uiConsolleAreaPrint(ex.getMessage() +"\n");
            }
        } catch (FileNotFoundException ex) {
             uiConsolleAreaPrint(ex.getMessage() +"\n");
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                 uiConsolleAreaPrint(ex.getMessage() +"\n");
            }
        }
   }
     public void SetUpServerSide()
    {
        uiPanelMain.setEnabled(true);
        treeRoot = new javax.swing.tree.DefaultMutableTreeNode("Clients");
        uiTreeHost.setModel(new javax.swing.tree.DefaultTreeModel(treeRoot));
        DisplayConsole.uiConsole = uiConsolleArea;    
        DisplayConsole.treeRoot = treeRoot;
        CreateServerSide();
        ConnectEnable(false);
        uiPanelMain.setVisible(true);
        uiTabLeft.remove(1);
        uiTabRight.remove(1);
        
    }
     
     public void CreateServerSide()
     {
        
        lib.UpdateServerConfigXmlFile(port);
        //ThreadConnectNetwork thread =  new ThreadConnectNetwork(uiConsolleArea);
        //thread.start();  
        try
        {
            FileSystemXmlApplicationContext context= new FileSystemXmlApplicationContext(path + "/rmiServerAppContext.xml"); 

            IRMIUserService coordinator = (IRMIUserService) context.getBean("RMIUserService");   
            coordinator.setKeyNumber(keyNumber);
            uiConsolleAreaPrint("Coordinator has established the network.\n");
            uiConsolleAreaPrint("Key Number: "+keyNumber+".\n");
            uiConsolleAreaPrint("Waiting the Client join the network...\n");
        }
        catch (BeansException | IllegalStateException ex)
        {
           uiConsolleAreaPrint("Error: "+ ex +"\n");
        } 
     }
     
     private void PublishHost()
     {
       try
        {
            lib.UpdateServerConfigXmlFile(port);
            FileSystemXmlApplicationContext context= new FileSystemXmlApplicationContext(path + "/rmiServerAppContext.xml"); 

            rmiHost = (IRMIUserService) context.getBean("RMIUserService"); 
            rmiHost.SetDevice(user);
            DisplayConsole.uiConsole = uiConsolleArea;
        }
        catch (BeansException | IllegalStateException ex)
        {
           uiConsolleAreaPrint("Error: "+ ex +"\n");
        } 
     }
     private void ConnectEnable(boolean  enable)
    {
        uiItemAddHost.setEnabled(enable);
        uiItemDisconnect.setEnabled(enable);
    }     
     public void ClientSideConnectToServer(String host)
    {           
        uiPanelMain.setVisible(true);
        uiTabLeft.remove(0);
        uiPanelMain.setEnabled(true);
        ScanDevice.setEnabled(true);
        try
        {        
            LoadConfigDevice();
            
            lib.UpdateClientConfigXmlFile(host, port +"");
            FileSystemXmlApplicationContext context= new FileSystemXmlApplicationContext(path + "/rmiClientAppContext.xml"); 
            rmiCoordinator = (IRMIUserService) context.getBean("RMIUserService"); 
            
            List<User> userList = rmiCoordinator.getUserList();           
            long newUserId = 0;
            for(int i = 0; i <userList.size(); i++)
            {
                if(userList.get(i).getId()> newUserId)
                    newUserId = userList.get(i).getId();
            }
            //New User is created...		
            user.setId(newUserId+1);
            user.setName(machineName);
            user.setIp(machineIp); 
            user.keyNumber = keyNumber;
            port = 1000 + new Random().nextInt(98);
            user.Port = port;

           // Metering meter = new Metering();
//            meter.Id = 1;
//            meter.Name = machineName;
//            meter.Value = 12;
//            user.setMetering(meter);
//            user.setDeviceType(DeviceType.Pricing);
            //user.pricing.tariffProfile.rateCode = new String20("Rate 1111" + user.getId());
            if(!rmiCoordinator.addUser(user))
            {
                rmiCoordinator = null;
                uiConsolleAreaPrint("Can not connect to the Coordinator ["+host+"] because the Key Number is wrong. Please try to re-connect again.\n");
                return;
            }

            uiConsolleAreaPrint("Client " + user.getName() +" connected to network successful.\n");
            ConnectEnable(false);              
            if(rmiCoordinator!=null)
            {
                PublishHost();
                ApplyToShowTestCaseList();
            }
        } catch (Exception ex)
        {
            uiConsolleAreaPrint("Can not connect to the Coordinator ["+host+"]. Please recheck network before re-try join to Coordinator.\n");
            return;
        }
        UpdateDeviceList();    
    }    
    private void SetEnableDeviceButton(boolean enable)
    {
        uibtDeviceApply.setEnabled(enable);
        uibtDeviceReset.setEnabled(enable);
    }
    
    private void UpdateDeviceList()
    {
        try
        {
            deviceList = rmiCoordinator.GetDeviceList();
        }
        catch (Exception ex)
        {
            deviceList = new ArrayList<DeviceInfo>();
            uiConsolleAreaPrint("The Coordinator is down. Can not get new client list.");
            return;
        }  
        uiDeviceLIst.setModel(new DefaultComboBoxModel());
        DeviceInfo localhost = new DeviceInfo();
        if(deviceList.size()>0)
        {
            Vector comboBoxItems=new Vector();
            //comboBoxItems.add(machineIp +": This is me");
            for(DeviceInfo device: deviceList)
            {
                if(device.IP.equals(machineIp) && device.Name.equals(machineName)&& device.Port == port)
                    localhost = device;
                else                    
                    comboBoxItems.add(device.IP +": "+ device.Name);
            }  
            deviceList.remove(localhost);
            DefaultComboBoxModel model = new DefaultComboBoxModel(comboBoxItems);
            uiDeviceLIst.setModel(model); 
            if(deviceList.size()>0)
                ConnectToServerSideForTesting(0);
        }
    }
    
    private void SetTestCaseModel()
    {
        uiTestCaseModel = new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "FileName", "Spec", "Selected", "Status", "Test Cases", "Required", "Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
        
        uitbTestCase.setModel(uiTestCaseModel);
        uitbTestCase.getColumn("Status").setCellRenderer(clrender);
        library.SetTableTestCaseLayout(uitbTestCase);
    }    
   
    private void SetFunctionSetModel()
    {
        
        functionSetModel = new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Function Set", "Server", "Client"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
        
        uiTableFunctionSet.setModel(functionSetModel);
        library.SetTableFunctionSetLayout(uiTableFunctionSet); 
    }
     private void AddHostList()
    {
         treeRoot = new javax.swing.tree.DefaultMutableTreeNode("Ember Host");
         uiTreeHost.setModel(new javax.swing.tree.DefaultTreeModel(treeRoot));
         
         InetAddress addr;
        try {
            addr = InetAddress.getLocalHost();
            clientHost = addr.getHostAddress();
        } catch (UnknownHostException ex)
        {}
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenu1 = new java.awt.PopupMenu();
        uiPanelMain = new javax.swing.JPanel();
        uiSplitPanel = new javax.swing.JSplitPane();
        uiPanelLeft = new javax.swing.JPanel();
        uiTabLeft = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        uiTreeHost = new javax.swing.JTree();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        uiCombDevice = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        uiTableFunctionSet = new javax.swing.JTable();
        uibtDeviceReset = new javax.swing.JButton();
        uibtDeviceApply = new javax.swing.JButton();
        uiPanelRight = new javax.swing.JPanel();
        uiTabRight = new javax.swing.JTabbedPane();
        uiTabCommandLine = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        uiCommandLine = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        uiConsolleArea = new javax.swing.JTextArea();
        uiCheckAutoScrollCommandLine = new javax.swing.JCheckBox();
        jPanel11 = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        uitbTestCase = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        uiDeviceLIst = new javax.swing.JComboBox();
        jPanel6 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        uiConsoleTestCase = new javax.swing.JTextPane();
        uibtSkipRun = new javax.swing.JButton();
        uibtRunTestScript = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        uiItemReloadTestcases = new javax.swing.JMenuItem();
        uiItemLoadConfigFromFile = new javax.swing.JMenuItem();
        uiItemSaveConfigToFile = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        uiSaveCommandLineLog = new javax.swing.JMenuItem();
        uiSaveOutTestCaseLog = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        uiViewCommandLineLog = new javax.swing.JMenuItem();
        uiViewOutTestCaseLog = new javax.swing.JMenuItem();
        uiItemClose = new javax.swing.JMenuItem();
        uiItemConnect = new javax.swing.JMenu();
        uiItemAddHost = new javax.swing.JMenuItem();
        uiItemDisconnect = new javax.swing.JMenuItem();
        ScanDevice = new javax.swing.JMenuItem();
        uiMenuConfigration = new javax.swing.JMenu();
        uiItemConfiguration = new javax.swing.JMenuItem();
        uiMenuHelp = new javax.swing.JMenu();
        uiItemViewSpec = new javax.swing.JMenuItem();
        uiItemHelp = new javax.swing.JMenuItem();

        popupMenu1.setLabel("popupMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("NTS Zigbee Tool");
        setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        setForeground(new java.awt.Color(102, 102, 102));
        setMinimumSize(new java.awt.Dimension(800, 600));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        uiSplitPanel.setDividerLocation(300);
        uiSplitPanel.setDividerSize(4);
        uiSplitPanel.setOpaque(false);

        uiPanelLeft.setPreferredSize(new java.awt.Dimension(200, 494));

        uiTabLeft.setAlignmentX(0.0F);
        uiTabLeft.setAlignmentY(0.0F);
        uiTabLeft.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        uiTabLeft.setInheritsPopupMenu(true);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        uiTreeHost.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        uiTreeHost.setVerifyInputWhenFocusTarget(false);
        jScrollPane1.setViewportView(uiTreeHost);
        uiTreeHost.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
                .addGap(37, 37, 37))
        );

        uiTabLeft.addTab("Hosts", jPanel1);

        jLabel2.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        jLabel2.setText("Select device type:");
        jLabel2.setAlignmentY(0.0F);
        jLabel2.setFocusTraversalPolicyProvider(true);
        jLabel2.setIconTextGap(1);
        jLabel2.setInheritsPopupMenu(false);
        jLabel2.setOpaque(true);

        uiCombDevice.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        uiCombDevice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uiCombDeviceActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        jLabel3.setText("Select function set:");

        uiTableFunctionSet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Function Set", "Server", "Client"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        uiTableFunctionSet.setRowHeight(20);
        uiTableFunctionSet.setRowSelectionAllowed(false);
        uiTableFunctionSet.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                uiTableFunctionSetMouseMoved(evt);
            }
        });
        uiTableFunctionSet.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                uiTableFunctionSetComponentAdded(evt);
            }
        });
        jScrollPane3.setViewportView(uiTableFunctionSet);

        uibtDeviceReset.setFont(new java.awt.Font("Segoe UI Symbol", 0, 11)); // NOI18N
        uibtDeviceReset.setText("Reset");
        uibtDeviceReset.setEnabled(false);
        uibtDeviceReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uibtDeviceResetActionPerformed(evt);
            }
        });

        uibtDeviceApply.setFont(new java.awt.Font("Segoe UI Symbol", 0, 11)); // NOI18N
        uibtDeviceApply.setText("Apply");
        uibtDeviceApply.setEnabled(false);
        uibtDeviceApply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uibtDeviceApplyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(uiCombDevice, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 142, Short.MAX_VALUE)
                        .addComponent(uibtDeviceApply)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(uibtDeviceReset)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(uiCombDevice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(uibtDeviceReset, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(uibtDeviceApply, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        uiTabLeft.addTab("Device Type", jPanel2);

        javax.swing.GroupLayout uiPanelLeftLayout = new javax.swing.GroupLayout(uiPanelLeft);
        uiPanelLeft.setLayout(uiPanelLeftLayout);
        uiPanelLeftLayout.setHorizontalGroup(
            uiPanelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(uiTabLeft)
        );
        uiPanelLeftLayout.setVerticalGroup(
            uiPanelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, uiPanelLeftLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(uiTabLeft))
        );

        uiTabLeft.getAccessibleContext().setAccessibleName("");

        uiSplitPanel.setLeftComponent(uiPanelLeft);

        uiTabRight.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N

        jLabel1.setText("Command:");

        uiCommandLine.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                uiCommandLineKeyPressed(evt);
            }
        });

        jScrollPane2.setWheelScrollingEnabled(false);

        uiConsolleArea.setEditable(false);
        uiConsolleArea.setColumns(20);
        uiConsolleArea.setRows(5);
        uiConsolleArea.setMargin(new java.awt.Insets(5, 5, 5, 5));
        jScrollPane2.setViewportView(uiConsolleArea);

        uiCheckAutoScrollCommandLine.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        uiCheckAutoScrollCommandLine.setSelected(true);
        uiCheckAutoScrollCommandLine.setText("Auto Scroll");
        uiCheckAutoScrollCommandLine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uiCheckAutoScrollCommandLineActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout uiTabCommandLineLayout = new javax.swing.GroupLayout(uiTabCommandLine);
        uiTabCommandLine.setLayout(uiTabCommandLineLayout);
        uiTabCommandLineLayout.setHorizontalGroup(
            uiTabCommandLineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(uiTabCommandLineLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(uiTabCommandLineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, uiTabCommandLineLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(uiCommandLine)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(uiCheckAutoScrollCommandLine))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE))
                .addContainerGap())
        );
        uiTabCommandLineLayout.setVerticalGroup(
            uiTabCommandLineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(uiTabCommandLineLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(uiTabCommandLineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(uiCommandLine, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(uiCheckAutoScrollCommandLine))
                .addGap(9, 9, 9))
        );

        uiTabRight.addTab("Command Line", uiTabCommandLine);

        jSplitPane1.setBorder(null);
        jSplitPane1.setDividerLocation(350);
        jSplitPane1.setDividerSize(4);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setLastDividerLocation(350);

        uitbTestCase.setFont(new java.awt.Font("Segoe UI Symbol", 0, 11)); // NOI18N
        uitbTestCase.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "FileName", "Spec", "Selected", "Status", "Test Cases", "Required", "Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        uitbTestCase.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                uitbTestCaseKeyPressed(evt);
            }
        });
        jScrollPane7.setViewportView(uitbTestCase);

        jLabel4.setFont(new java.awt.Font("Segoe UI Symbol", 0, 11)); // NOI18N
        jLabel4.setText("Test on host:");

        uiDeviceLIst.setFont(new java.awt.Font("Segoe UI Symbol", 0, 11)); // NOI18N
        uiDeviceLIst.setMinimumSize(new java.awt.Dimension(28, 22));
        uiDeviceLIst.setPreferredSize(new java.awt.Dimension(28, 22));
        uiDeviceLIst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uiDeviceLIstActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(uiDeviceLIst, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(uiDeviceLIst, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE))
        );

        jSplitPane1.setTopComponent(jPanel5);

        uiConsoleTestCase.setEditable(false);
        jScrollPane4.setViewportView(uiConsoleTestCase);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jSplitPane1.setRightComponent(jPanel6);

        uibtSkipRun.setFont(new java.awt.Font("Segoe UI Symbol", 0, 11)); // NOI18N
        uibtSkipRun.setText("Skip");
        uibtSkipRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uibtSkipRunActionPerformed(evt);
            }
        });

        uibtRunTestScript.setFont(new java.awt.Font("Segoe UI Symbol", 0, 11)); // NOI18N
        uibtRunTestScript.setText("Run");
        uibtRunTestScript.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uibtRunTestScriptActionPerformed(evt);
            }
        });

        jCheckBox1.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        jCheckBox1.setSelected(true);
        jCheckBox1.setText("Auto Scroll");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(uibtRunTestScript, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(uibtSkipRun, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jCheckBox1)
                .addContainerGap())
            .addComponent(jSplitPane1)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jSplitPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(uibtSkipRun, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(uibtRunTestScript, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox1))
                .addGap(10, 10, 10))
        );

        uiTabRight.addTab("Test Cases", jPanel11);

        javax.swing.GroupLayout uiPanelRightLayout = new javax.swing.GroupLayout(uiPanelRight);
        uiPanelRight.setLayout(uiPanelRightLayout);
        uiPanelRightLayout.setHorizontalGroup(
            uiPanelRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(uiTabRight)
        );
        uiPanelRightLayout.setVerticalGroup(
            uiPanelRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(uiPanelRightLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(uiTabRight))
        );

        uiSplitPanel.setRightComponent(uiPanelRight);

        javax.swing.GroupLayout uiPanelMainLayout = new javax.swing.GroupLayout(uiPanelMain);
        uiPanelMain.setLayout(uiPanelMainLayout);
        uiPanelMainLayout.setHorizontalGroup(
            uiPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 790, Short.MAX_VALUE)
            .addGroup(uiPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(uiSplitPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 492, Short.MAX_VALUE))
        );
        uiPanelMainLayout.setVerticalGroup(
            uiPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 579, Short.MAX_VALUE)
            .addGroup(uiPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(uiSplitPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE))
        );

        jMenuBar1.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N

        jMenu1.setText("File");
        jMenu1.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N

        uiItemReloadTestcases.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        uiItemReloadTestcases.setText("Reload Test Cases");
        uiItemReloadTestcases.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uiItemReloadTestcasesActionPerformed(evt);
            }
        });
        jMenu1.add(uiItemReloadTestcases);

        uiItemLoadConfigFromFile.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        uiItemLoadConfigFromFile.setText("Load Config From File");
        uiItemLoadConfigFromFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uiItemLoadConfigFromFileActionPerformed(evt);
            }
        });
        jMenu1.add(uiItemLoadConfigFromFile);

        uiItemSaveConfigToFile.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        uiItemSaveConfigToFile.setText("Save Config To File");
        uiItemSaveConfigToFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uiItemSaveConfigToFileActionPerformed(evt);
            }
        });
        jMenu1.add(uiItemSaveConfigToFile);

        jMenu3.setText("Save Log File");
        jMenu3.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N

        uiSaveCommandLineLog.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        uiSaveCommandLineLog.setText("Command Line");
        uiSaveCommandLineLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uiSaveCommandLineLogActionPerformed(evt);
            }
        });
        jMenu3.add(uiSaveCommandLineLog);

        uiSaveOutTestCaseLog.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        uiSaveOutTestCaseLog.setText("Out Test Cases");
        uiSaveOutTestCaseLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uiSaveOutTestCaseLogActionPerformed(evt);
            }
        });
        jMenu3.add(uiSaveOutTestCaseLog);

        jMenu1.add(jMenu3);

        jMenu4.setText("View Log File");
        jMenu4.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N

        uiViewCommandLineLog.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        uiViewCommandLineLog.setText("Command Line");
        uiViewCommandLineLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uiViewCommandLineLogActionPerformed(evt);
            }
        });
        jMenu4.add(uiViewCommandLineLog);

        uiViewOutTestCaseLog.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        uiViewOutTestCaseLog.setText("Out Test Cases");
        uiViewOutTestCaseLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uiViewOutTestCaseLogActionPerformed(evt);
            }
        });
        jMenu4.add(uiViewOutTestCaseLog);

        jMenu1.add(jMenu4);

        uiItemClose.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        uiItemClose.setText("Close");
        uiItemClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uiItemCloseActionPerformed(evt);
            }
        });
        jMenu1.add(uiItemClose);

        jMenuBar1.add(jMenu1);

        uiItemConnect.setText("Network");
        uiItemConnect.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        uiItemConnect.setInheritsPopupMenu(true);
        uiItemConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uiItemConnectActionPerformed(evt);
            }
        });

        uiItemAddHost.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        uiItemAddHost.setText("Set Up Cordinator");
        uiItemAddHost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uiItemAddHostActionPerformed(evt);
            }
        });
        uiItemConnect.add(uiItemAddHost);

        uiItemDisconnect.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        uiItemDisconnect.setText("Disconnection");
        uiItemDisconnect.setEnabled(false);
        uiItemDisconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uiItemDisconnectActionPerformed(evt);
            }
        });
        uiItemConnect.add(uiItemDisconnect);

        ScanDevice.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        ScanDevice.setText("Scan all device");
        ScanDevice.setEnabled(false);
        ScanDevice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ScanDeviceActionPerformed(evt);
            }
        });
        uiItemConnect.add(ScanDevice);
        ScanDevice.getAccessibleContext().setAccessibleName("Scan All Device");

        jMenuBar1.add(uiItemConnect);

        uiMenuConfigration.setText("Configration");
        uiMenuConfigration.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        uiMenuConfigration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uiMenuConfigrationActionPerformed(evt);
            }
        });

        uiItemConfiguration.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        uiItemConfiguration.setText("Enter DUT Identification");
        uiItemConfiguration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uiItemConfigurationActionPerformed(evt);
            }
        });
        uiMenuConfigration.add(uiItemConfiguration);

        jMenuBar1.add(uiMenuConfigration);

        uiMenuHelp.setText("Help");
        uiMenuHelp.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N

        uiItemViewSpec.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        uiItemViewSpec.setText("View Specification");
        uiItemViewSpec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uiItemViewSpecActionPerformed(evt);
            }
        });
        uiMenuHelp.add(uiItemViewSpec);

        uiItemHelp.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        uiItemHelp.setText("About us");
        uiItemHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uiItemHelpActionPerformed(evt);
            }
        });
        uiMenuHelp.add(uiItemHelp);

        jMenuBar1.add(uiMenuHelp);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(uiPanelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(uiPanelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void uiItemCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uiItemCloseActionPerformed
        // TODO add your handling code here:
        
        try
        {
            if(rmiCoordinator != null)
                rmiCoordinator.deleteUser(user);
        }
        catch (Exception ex)
        {
            
        }
        this.dispose();
    }//GEN-LAST:event_uiItemCloseActionPerformed

    private void uiItemAddHostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uiItemAddHostActionPerformed
        // TODO add your handling code here:
        
          SetUpHost();
    }//GEN-LAST:event_uiItemAddHostActionPerformed

    public void SetUpHost()
    {
        ConfigureNetwork frm = new ConfigureNetwork(this,true, machineIp);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        
                    // Determine the new location of the window
                    int w = frm.getSize().width;
                    int h = frm.getSize().height;
                    int x = (dim.width-w)/2;
                    int y = (dim.height-h)/2;        
                    // Move the window
                    
                    frm.setLocation(x, y);
                    frm.show(); 
                   if(frm.isConnect)
                   {
                       keyNumber = frm.keyNumber;
                       if(frm.isServer)
                       {
                          // UUID uuid = UUID.randomUUID();
                          // keyNumber = uuid.toString();
                           SetUpServerSide();
                           this.setTitle("NTS Tool - Coordinator"); 
                       }
                       else
                       {                           
                           ClientSideConnectToServer(frm.host);
                           this.setTitle("NTS Tool - Client - "+ user.getId() + ": " + user.getName()); 
                           //remind
                           //Threadupdated = new ThreadUpdateData(this);
                           //Threadupdated.start();
                           //PublishHost();
                       }
                  }  
    }
    
    private void uiCommandLineKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_uiCommandLineKeyPressed
        // TODO add your handling code here: 
        try
        {
            if (evt.getKeyCode() == 10) {
                String command = uiCommandLine.getText();
                if(command.length()>0)
                {
                    uiCommandLine.setText(null);
                    NTSPrint(command, true);
                    callscript.ExecutiveCommandLine(inter, command);
                }
            }
        }catch (Exception ex)
        {
            uiConsolleAreaPrint(ex.getMessage()+"\n");
        }
    }//GEN-LAST:event_uiCommandLineKeyPressed
    public void SendCommand(String cmd)
    {
        if(rmiClient!=null)
        {
            List<String> result = null;
            result =  rmiClient.SendRequest(machineName, cmd);
            testcaseResult.status = result.get(0).equals("T");
            testcaseResult.result = result.get(1);
            PrintTestConsole(testcaseResult.status, testcaseResult.result, true);
        }
        else 
        {
            testcaseResult.status = false;
            testcaseResult.result = "Cannot connect to client, Please re-check connection.";
            PrintTestConsole(testcaseResult.status, testcaseResult.result, true);
        }
    }
    public void SendCommand(String cmd, String context)
    {
        if(rmiClient!=null)
        {
            List<String> result = null;
            result =  rmiClient.SendRequest(machineName, cmd, context);
            testcaseResult.status = result.get(0).equals("T");
            testcaseResult.result = result.get(1);
            PrintTestConsole(testcaseResult.status, testcaseResult.result, true);
        }
        else 
        {
            testcaseResult.status = false;
            testcaseResult.result = "Cannot connect to client, Please re-check connection.";
            PrintTestConsole(testcaseResult.status, testcaseResult.result, true);
        }
    }
    public void NTSSendCommand(String cmd)
    {
        if(rmiClient!=null)
        {
            List<String> result = null;
            result =  rmiClient.SendRequest(machineName, cmd);
            testcaseResult.status = result.get(0).equals("T");
            testcaseResult.result = result.get(1);
            NTSPrint(testcaseResult.result, true);
        }
        else 
        {
            testcaseResult.status = false;
            testcaseResult.result = "Cannot connect to client, Please re-check connection.";
            NTSPrint(testcaseResult.result, true);
        }
    }
    public void NTSSendCommand(String cmd, String context)
    {
        if(rmiClient!=null)
        {
            List<String> result = null;
            result =  rmiClient.SendRequest(machineName, cmd, context);
            testcaseResult.status = result.get(0).equals("T");
            testcaseResult.result = result.get(1);
            NTSPrint(testcaseResult.result, true);
        }
        else 
        {
            testcaseResult.status = false;
            testcaseResult.result = "Cannot connect to client, Please re-check connection.";
            NTSPrint(testcaseResult.result, true);
        }
    }
    
    public String ReadxmlFile(String fileName)
    {
        String str="";

        //reading   
        try{
            InputStream ips=new FileInputStream(new File(fileName)); 
            InputStreamReader ipsr=new InputStreamReader(ips);
            BufferedReader br=new BufferedReader(ipsr);
            String line;
            while ((line=br.readLine())!=null){
                System.out.println(line);
                str +=line+"\n";
            }
            br.close(); 
        }       
        catch (Exception e){
           return "";
        }
        return  str;
    }
    private void uibtDeviceResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uibtDeviceResetActionPerformed
        // TODO add your handling code here:
        uiCombDevice.setSelectedIndex(deviceSelectIndex);
        objClas.UpdateDefaultFunctionSet(uiTableFunctionSet,arrayfunList);
        SetEnableDeviceButton(false);
    }//GEN-LAST:event_uibtDeviceResetActionPerformed

    private void uibtDeviceApplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uibtDeviceApplyActionPerformed
        // TODO add your handling code here:
        ApplyToShowTestCaseList();             
    }//GEN-LAST:event_uibtDeviceApplyActionPerformed

    public void ApplyToShowTestCaseList()
    {
        uiTabRight.setSelectedIndex(1);
      deviceSelectIndex = uiCombDevice.getSelectedIndex();
      ArrayList<FunctionSet> funcList = new ArrayList<FunctionSet>();
      arrayfunList  = new ArrayList<String>();
      for(int i =0; i< uiTableFunctionSet.getModel().getRowCount(); i++)
      {
          if(uiTableFunctionSet.getModel().getValueAt(i, 2) == true ||uiTableFunctionSet.getModel().getValueAt(i, 3) == true)
          {
              if(uiTableFunctionSet.getModel().getValueAt(i, 2) == true)
                  funcList.add(new FunctionSet(uiTableFunctionSet.getModel().getValueAt(i, 1).toString(),"T"));
              if(uiTableFunctionSet.getModel().getValueAt(i, 3) == true)
                funcList.add(new FunctionSet(uiTableFunctionSet.getModel().getValueAt(i, 1).toString(),"F"));
          
              
              if(uiTableFunctionSet.getModel().getValueAt(i, 2) == true && uiTableFunctionSet.getModel().getValueAt(i, 3) == true)
                arrayfunList.add(uiTableFunctionSet.getValueAt(i, 0).toString() +"-2");
              else if(uiTableFunctionSet.getModel().getValueAt(i, 2) == true)
                arrayfunList.add(uiTableFunctionSet.getValueAt(i, 0).toString() +"-1");
              else
                arrayfunList.add(uiTableFunctionSet.getValueAt(i, 0).toString() +"-0");
          }
      }
              
        SetTestCaseModel();
        objClas.AddNewTestCase(uiTestCaseModel,funcList);
        SetEnableDeviceButton(false);
    }
    private void uiItemConfigurationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uiItemConfigurationActionPerformed
        // TODO add your handling code here:
        ConfigurationForm cofig = new ConfigurationForm( this);
         Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        // Determine the new location of the window
        int w = cofig.getSize().width;
        int h = cofig.getSize().height;
        int x = (dim.width-w)/2;
        int y = (dim.height-h)/2;

        // Move the window
        cofig.setLocation(x, y);
        cofig.show();
        
    }//GEN-LAST:event_uiItemConfigurationActionPerformed

    private void uibtSkipRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uibtSkipRunActionPerformed
        // TODO add your handling code here:
        isSkip = true;
        
    }//GEN-LAST:event_uibtSkipRunActionPerformed

    private void uibtRunTestScriptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uibtRunTestScriptActionPerformed
        // TODO add your handling code here:
       if(rmiClient == null)
       {
           SetTextConsole(Color.black,null, "Can not connect to the server. Please select other server to for testing.", true);
           return;
       }
        
       uibtRunTestScript.setEnabled(false);
       isSkip = false;
       RunTestCase(0);
        
       
    }//GEN-LAST:event_uibtRunTestScriptActionPerformed

    public void UpdateTestCasesStatus()
    {
        statusList = new ArrayList<List>();
    }
    public void ExcutiveTestCase(int index)
    {
       String fileName = uitbTestCase.getModel().getValueAt(index, 0).toString();
       Testcase testcase = library.ReadScriptlFile(fileName);
       String status = "F";
       boolean hostDow = false;
       if(testcase!=null)
       {    
            SetTextConsole(Color.black,null,"Send at: "+GetCurrentDateTime()+"\n", true);
            try
            {
                if(rmiClient!=null)                
                  callscript.ExcutiveTestCase(inter, fileName);
                else 
                    hostDow = true;
            }
            catch (Exception e)
            {
                hostDow = true;
            }            
            if(hostDow)
            {
                rmiClient = null;
                uibtRunTestScript.setEnabled(true);
                uitbTestCase.getModel().setValueAt("Failed",index, 3);
                PrintTestConsole(false, "Can not connect to the server. Please select other server before send command", true);
                SetTextConsole(Color.black,null, "--------------------------------------------", true);
                isSkip = true;
                return;
            }
            if(testcaseResult.status)
            {
                status = "P";
                uitbTestCase.getModel().setValueAt("Passed",index, 3); 
            }
            else
            {
                
                uitbTestCase.getModel().setValueAt("Failed",index, 3);
            }           
            uitbTestCase.getColumn("Status").setCellRenderer(clrender);            
            SetTextConsole(Color.black, null,"--------------------------------------------", true);            
            List<String> newStatus = new ArrayList<String>();
            newStatus.add(status);
            newStatus.add(uitbTestCase.getModel().getValueAt(index, 0).toString());
            statusList.add(newStatus);
       }
    }
   
    public void RunTestCase(int index)
    {
        if(!isSkip)
        {
            if(index < uitbTestCase.getModel().getRowCount())
            {
                for(int i = index; i<uitbTestCase.getModel().getRowCount(); i++)
                {
                     if(uitbTestCase.getModel().getValueAt(i, 2) == true)
                    {               
                        SetTextConsole(Color.black, null, "Start running "+ uitbTestCase.getModel().getValueAt(i, 1).toString().replaceAll(".doc","") +" : "+uitbTestCase.getModel().getValueAt(i, 4) + "- " +uitbTestCase.getModel().getValueAt(i, 6), true);
                        uitbTestCase.getModel().setValueAt("Running",i, 3);                
                        ThreadRunTestCase thrun = new ThreadRunTestCase(this, i) ;
                        thrun.start();
                        return;
                    }
                }
            }
        }
        else
        {
            isSkip = false;
            SetTextConsole(Color.red,null,GetCurrentDateTime()+": Skiped running testcase.", true);
        }
        JOptionPane.showMessageDialog(this,"The system finished running test scripts.","NTS Tool",JOptionPane.INFORMATION_MESSAGE);
        uibtRunTestScript.setEnabled(true);      
        objClas.UpdateTestCases(statusList);
    }
    private String GetCurrentDateTime()
    {
        Date date = new Date();
        return date.toString();
    }
    private void uiCombDeviceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uiCombDeviceActionPerformed
        // TODO add your handling code here:
       defaultfuncList =   objClas.ChangeFunctionSetSelected(uiTableFunctionSet, uiCombDevice.getSelectedIndex());     
        
        SetEnableDeviceButton(true);
    }//GEN-LAST:event_uiCombDeviceActionPerformed

    private void uiTableFunctionSetComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_uiTableFunctionSetComponentAdded
        // TODO add your handling code here:
         SetEnableDeviceButton(true);
    }//GEN-LAST:event_uiTableFunctionSetComponentAdded

    private void uiTableFunctionSetMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_uiTableFunctionSetMouseMoved
        // TODO add your handling code here:
        boolean show = false;
        if(defaultfuncList.size()>0)
        {
           
            int row =uiTableFunctionSet.rowAtPoint(evt.getPoint());
            int column = uiTableFunctionSet.columnAtPoint(evt.getPoint());
            for(int i = 0 ;i<defaultfuncList.size(); i++)
            {
                String elem = defaultfuncList.get(i);
                if(uiTableFunctionSet.getModel().getValueAt(row, 0).toString().equals(elem.substring(0,elem.indexOf("-"))))
                {
                    String m = elem.substring(elem.indexOf("-")+1);
                    if((m.equals("2") && column>1)|| (m.equals("1") && column == 2) ||(m.equals("0")&&column ==3))
                    {
                        uiTableFunctionSet.setToolTipText("Mandatory");
                        show = true;
                    }   
                }
            }  
            if(!show)
               uiTableFunctionSet.setToolTipText(null);
        }
    }//GEN-LAST:event_uiTableFunctionSetMouseMoved

    private void uiCheckAutoScrollCommandLineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uiCheckAutoScrollCommandLineActionPerformed
        // TODO add your handling code here:
        if(uiCheckAutoScrollCommandLine.isSelected())
        {
            DefaultCaret caret = (DefaultCaret)uiConsolleArea.getCaret();
            caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
            uiConsolleArea.setCaretPosition(uiConsolleArea.getDocument().getLength());
           
        }
        else
        {
            DefaultCaret caret = (DefaultCaret)uiConsolleArea.getCaret();
            caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
        }
            
    }//GEN-LAST:event_uiCheckAutoScrollCommandLineActionPerformed
    private void uiConsolleAreaPrint(String msg)
    {
        uiConsolleArea.append(msg);
        if(uiCheckAutoScrollCommandLine.isSelected())
        {
          uiConsolleArea.setCaretPosition(uiConsolleArea.getDocument().getLength());
        }
    }
    
    private void uiItemReloadTestcasesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uiItemReloadTestcasesActionPerformed
        // TODO add your handling code here:
        
//        try
//        {
//            
//            File folder = new File(System.getProperty("user.dir")+"//Scripts");
//            File[] listOfFiles = folder.listFiles();
//            ArrayList<String[]> arrayList = new ArrayList<String[]>();
//           
//            for (File listOfFile : listOfFiles)
//            {
//               
//    
//           FileInputStream fstream = new FileInputStream(listOfFile.getPath());
//            
//            DataInputStream in = new DataInputStream(fstream);
//            BufferedReader br = new BufferedReader(new InputStreamReader(in));
//            String strLine;
//            //Read File Line By Line
//            strLine = br.readLine();
//            
//            strLine = strLine.substring(strLine.indexOf("\"")+1);
//            strLine = strLine.substring(0,strLine.indexOf("\""));
//            String[] header = strLine.split(",");
//            String[] paramTestcase = {objClas.getTestSuiteID(header[0]),
//                                        header[0].trim(),
//                                        header[1].trim(),
//                                        header[2].trim(),
//                                        listOfFile.getName(),
//                                        listOfFile.getName().replace(".scr", ".doc"),
//                                        header[3].trim()};
//            arrayList.add(paramTestcase);
//            //objClas.insertTestCase(paramTestcase);
//            uiConsolleAreaPrint("Added test script "+listOfFile.getName() +"\n");
//            in.close();            
//    
//            }
//            library.CreateExcelFile(arrayList);
//                }catch (Exception e){
//            }  
        
        
             library.ReLoadTestCase();
            for(int j = uitbTestCase.getModel().getRowCount()-1; j>=0; j--)
            {
                uiTestCaseModel.removeRow(j);
            } 
            ApplyToShowTestCaseList(); 
        
    }//GEN-LAST:event_uiItemReloadTestcasesActionPerformed

    private void uiItemViewSpecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uiItemViewSpecActionPerformed
        // TODO add your handling code here:
        
        if(uitbTestCase.getModel().getRowCount()>0 && uitbTestCase.getSelectedRow()>=0)
        {
            String specFile = uitbTestCase.getModel().getValueAt(uitbTestCase.getSelectedRow(), 0).toString();
            specFile = path+"\\Specs\\"+specFile.replace(".xml", ".doc");
           try
           {                
                library.OpenDocumentFile(specFile);
           }catch (Exception ex)
           {
               SetTextConsole(Color.black, null, "Can not found "+specFile+" file", true);
           }
                  
        }
        
    }//GEN-LAST:event_uiItemViewSpecActionPerformed

    private void uitbTestCaseKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_uitbTestCaseKeyPressed
        // TODO add your handling code here:
        
        if(evt.isAltDown() && evt.getKeyCode()== 86)
        {
            if(uitbTestCase.getModel().getRowCount()>0 && uitbTestCase.getSelectedRow()>=0)
        {
            String specFile = uitbTestCase.getModel().getValueAt(uitbTestCase.getSelectedRow(), 2).toString();
           try
           {                
                library.OpenDocumentFile(specFile);
           }catch (Exception ex)
           {
               SetTextConsole(Color.black, null, "Can not open "+specFile+" file", false);
           }
                  
        }     
        }
    }//GEN-LAST:event_uitbTestCaseKeyPressed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
        
        if(jCheckBox1.isSelected())
        {
            DefaultCaret caret = (DefaultCaret)uiConsoleTestCase.getCaret();
            caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
            uiConsoleTestCase.setCaretPosition(uiConsoleTestCase.getDocument().getLength());
           
        }
        else
        {
            DefaultCaret caret = (DefaultCaret)uiConsoleTestCase.getCaret();
            caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
        }
        
            
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void uiMenuConfigrationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uiMenuConfigrationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_uiMenuConfigrationActionPerformed

    private void uiItemDisconnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uiItemDisconnectActionPerformed
        // TODO add your handling code here:
        // uiConsolleAreaPrint(uiTreeHost.getSelectionPath().toString()+"");

   }//GEN-LAST:event_uiItemDisconnectActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        try
        {
            if(rmiCoordinator != null)
                rmiCoordinator.deleteUser(user);
        }
        catch (Exception ex)
        {}
    }//GEN-LAST:event_formWindowClosing

    private void uiItemConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uiItemConnectActionPerformed
        // TODO add your handling code here:
                    ConfigureNetwork frm = new ConfigureNetwork(this,true,machineIp);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        
            // Determine the new location of the window
            int w = frm.getSize().width;
            int h = frm.getSize().height;
            int x = (dim.width-w)/2;
            int y = (dim.height-h)/2;        
            // Move the window

            frm.setLocation(x, y);
            frm.show(); 
            if(frm.isConnect)
            {
                if(frm.isServer)
                {
                    SetUpServerSide();
                    this.setTitle("NTS Tool - Coordinator"); 
                }
                else
                {
                    ClientSideConnectToServer(frm.host);
                    this.setTitle("NTS Tool - Client - "+ user.getId() + ": " + user.getName()); 
                }
            }
    }//GEN-LAST:event_uiItemConnectActionPerformed

    private void uiSaveCommandLineLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uiSaveCommandLineLogActionPerformed
        // TODO add your handling code here:
        library.SaveFile(uiConsolleArea,"NTS.log");
    }//GEN-LAST:event_uiSaveCommandLineLogActionPerformed

    private void uiViewCommandLineLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uiViewCommandLineLogActionPerformed
        // TODO add your handling code here:
        library.OpenFile("NTS.log");
    }//GEN-LAST:event_uiViewCommandLineLogActionPerformed

    private void uiSaveOutTestCaseLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uiSaveOutTestCaseLogActionPerformed
        // TODO add your handling code here:
        library.SaveFile(uiConsoleTestCase,"TestLog.log");
    }//GEN-LAST:event_uiSaveOutTestCaseLogActionPerformed

    private void uiViewOutTestCaseLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uiViewOutTestCaseLogActionPerformed
        // TODO add your handling code here:
        library.OpenFile("TestLog.log");
    }//GEN-LAST:event_uiViewOutTestCaseLogActionPerformed

    private void ScanDeviceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ScanDeviceActionPerformed
        // TODO add your handling code here:
        UpdateDeviceList();
    }//GEN-LAST:event_ScanDeviceActionPerformed

    private void uiDeviceLIstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uiDeviceLIstActionPerformed
        // TODO add your handling code here:
        int index = uiDeviceLIst.getSelectedIndex();
        ConnectToServerSideForTesting(index);
                    
    }//GEN-LAST:event_uiDeviceLIstActionPerformed
    
    private void ConnectToServerSideForTesting(int index)
    {        
        DeviceInfo workingDevice = deviceList.get(index);
        lib.UpdateClientConfigXmlFile(workingDevice.IP, workingDevice.Port+"");
        FileSystemXmlApplicationContext context= new FileSystemXmlApplicationContext(path + "/rmiClientAppContext.xml"); 
        rmiClient = (IRMIUserService) context.getBean("RMIUserService");  
    }
    
    private void uiItemLoadConfigFromFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uiItemLoadConfigFromFileActionPerformed
        // TODO add your handling code here:
        LoadConfigFileForFunctionSet();
    }//GEN-LAST:event_uiItemLoadConfigFromFileActionPerformed

    private void uiItemSaveConfigToFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uiItemSaveConfigToFileActionPerformed
        // TODO add your handling code here:
        if(lib != null && configFile!=null)
        {
            if(uiCombDevice.getSelectedIndex()==0)
                configFile.deviceType = "None";
            else
                configFile.deviceType = uiCombDevice.getSelectedItem().toString();
            List<FunctionSet> funcSets = new ArrayList<FunctionSet>();
            for(int i =0; i< uiTableFunctionSet.getModel().getRowCount(); i++)
            {
                if(uiTableFunctionSet.getModel().getValueAt(i, 2) == true || uiTableFunctionSet.getModel().getValueAt(i, 3) == true)
                {
                    FunctionSet func = new FunctionSet();
                    func.functionsetName = uiTableFunctionSet.getModel().getValueAt(i, 1).toString();
                    func.server = uiTableFunctionSet.getModel().getValueAt(i, 2) == true? "1" : "0";
                    func.client = uiTableFunctionSet.getModel().getValueAt(i, 3) == true? "1" : "0";
                    funcSets.add(func);
                }
            }
            configFile.FunctionSets = funcSets;
            lib.UpdateConfigProfileXmlFile(configFile);
        }
    }//GEN-LAST:event_uiItemSaveConfigToFileActionPerformed

    private void uiItemHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uiItemHelpActionPerformed

        FrmAboutUs frm = new FrmAboutUs();
         Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        // Determine the new location of the window
        int w = frm.getSize().width;
        int h = frm.getSize().height;
        int x = (dim.width-w)/2;
        int y = (dim.height-h)/2;

        // Move the window
        frm.setLocation(x, y);
        frm.show();
    }//GEN-LAST:event_uiItemHelpActionPerformed

    public void SetFontConsoleTescase( Color color)
    {
        Font font = new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 10);
       // uiConsoleTestCase.setFont(font);
        uiConsoleTestCase.setForeground(color);
    }
    public void SetTextConsole(Color c, Color b, String s, boolean isnewLine) { // better implementation--uses
        StyledDocument doc = uiConsoleTestCase.getStyledDocument();

        Style style = uiConsoleTestCase.addStyle("I'm a Style", null);
        StyleConstants.setForeground(style, c);
        if(b!=null)
            StyleConstants.setBackground(style, b);
        try 
        { 
            doc.insertString(doc.getLength(), (isnewLine?"\n":"") + s  ,style); 
        }
        catch (BadLocationException e){}        
  }
    public void UpdateDataForUser()
    {
        if(rmiCoordinator!= null)
        {
            User oldUser = rmiCoordinator.getUser(user.getId());
            if(oldUser!=null && oldUser.IsUpdated)
            {
                user = oldUser;
                user.IsUpdated = false;
                rmiCoordinator.addUser(user);
            }
        } 
       ThreadUpdateData threadupdated = new ThreadUpdateData(this);
       threadupdated.start();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NTSMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NTSMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NTSMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NTSMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                   new NTSMain().setVisible(true);                
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem ScanDevice;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSplitPane jSplitPane1;
    private java.awt.PopupMenu popupMenu1;
    private javax.swing.JCheckBox uiCheckAutoScrollCommandLine;
    private javax.swing.JComboBox uiCombDevice;
    private javax.swing.JTextField uiCommandLine;
    private javax.swing.JTextPane uiConsoleTestCase;
    public static javax.swing.JTextArea uiConsolleArea;
    private javax.swing.JComboBox uiDeviceLIst;
    private javax.swing.JMenuItem uiItemAddHost;
    private javax.swing.JMenuItem uiItemClose;
    private javax.swing.JMenuItem uiItemConfiguration;
    private javax.swing.JMenu uiItemConnect;
    private javax.swing.JMenuItem uiItemDisconnect;
    private javax.swing.JMenuItem uiItemHelp;
    private javax.swing.JMenuItem uiItemLoadConfigFromFile;
    private javax.swing.JMenuItem uiItemReloadTestcases;
    private javax.swing.JMenuItem uiItemSaveConfigToFile;
    private javax.swing.JMenuItem uiItemViewSpec;
    private javax.swing.JMenu uiMenuConfigration;
    private javax.swing.JMenu uiMenuHelp;
    private javax.swing.JPanel uiPanelLeft;
    private javax.swing.JPanel uiPanelMain;
    private javax.swing.JPanel uiPanelRight;
    private javax.swing.JMenuItem uiSaveCommandLineLog;
    private javax.swing.JMenuItem uiSaveOutTestCaseLog;
    private javax.swing.JSplitPane uiSplitPanel;
    private javax.swing.JPanel uiTabCommandLine;
    private javax.swing.JTabbedPane uiTabLeft;
    private javax.swing.JTabbedPane uiTabRight;
    private javax.swing.JTable uiTableFunctionSet;
    private javax.swing.JTree uiTreeHost;
    private javax.swing.JMenuItem uiViewCommandLineLog;
    private javax.swing.JMenuItem uiViewOutTestCaseLog;
    private javax.swing.JButton uibtDeviceApply;
    private javax.swing.JButton uibtDeviceReset;
    private javax.swing.JButton uibtRunTestScript;
    private javax.swing.JButton uibtSkipRun;
    private javax.swing.JTable uitbTestCase;
    // End of variables declaration//GEN-END:variables
}

class ColorRenderer extends JLabel implements TableCellRenderer
    {
     private String columnName;

    @Override
    public void setFont(Font font) {
        
        super.setFont(font); //To change body of generated methods, choose Tools | Templates.
    }
     public ColorRenderer(String column)
         {
         this.columnName = column;
         setOpaque(true);
     }
     public Component getTableCellRendererComponent(JTable table, Object value,boolean isSelected, boolean hasFocus, int row, int column)
         {
         Object columnValue=table.getValueAt(row,table.getColumnModel().getColumnIndex(columnName));
        
         if (value != null) setText(value.toString());
         if(isSelected)
             {
             setBackground(table.getSelectionBackground());
             setForeground(table.getSelectionForeground());
             setFont(new Font("Segoe UI Symbol", 0, 12));
         }
         else
             {
             setBackground(table.getBackground());
             setForeground(table.getForeground());
             if (columnValue.equals("Passed")) setBackground(java.awt.Color.green);
             else if (columnValue.equals("Failed")) setBackground(java.awt.Color.red);
             else setBackground(java.awt.Color.white);
            setFont(new Font("Segoe UI Symbol", 0, 12));
         }
         return this;
     }
}
