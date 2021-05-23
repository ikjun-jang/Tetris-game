import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.SwingUtilities;

class Option extends javax.swing.JFrame {
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    
    public Option() {
        super("Option");
        initComponents();
        CvTetris.flag = true;
    }
    
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();

        jButton1.setText("Apply");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Scoring Factor (1-10)");
        jLabel2.setText("Number of Rows");
        jLabel3.setText("Speed Factor (0.1-1.0)");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(jTextField2)
                    .addComponent(jTextField3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        CvTetris.M = Integer.parseInt(jTextField1.getText());
        CvTetris.N = Integer.parseInt(jTextField2.getText());
        CvTetris.S = Float.parseFloat(jTextField3.getText());
    }    
}

public class Tetris extends javax.swing.JFrame {

    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private java.awt.Canvas canvas;
    
    public static void main(String[] args) {
        new Tetris();
    }

    Tetris()
    {
        super("Tetris");
        addWindowListener(new WindowAdapter()
            {public void windowClosing(WindowEvent e){System.exit(0);}});
        initComponents();
        show();
    }
    
    private void initComponents() {
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        canvas = new CvTetris();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        jMenu1.setText("Menu");
        jMenuItem1.setText("Option");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);
        jMenuBar1.add(jMenu1);
        setJMenuBar(jMenuBar1);
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(canvas, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(canvas, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }
    
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Option().setVisible(true);
            }
        });
    } 
}

class Point2D
{
    float x, y;
    Point2D(float x, float y){this.x=x; this.y=y;}
}

class Tools2D
{
    static float area2(Point2D a, Point2D b, Point2D c)
    {
    	return (a.x-c.x) * (b.y-c.y) - (a.y-c.y) * (b.x-c.x);
    }

    static boolean insidePolygon(Point2D p, Point2D[] pol)
    {
	int n = pol.length, j = n-1;
	boolean b = false;
	float x = p.x, y = p.y;
	for(int i=0; i<n; i++)
	{
            if((pol[j].y <= y && y < pol[i].y && Tools2D.area2(pol[j], pol[i], p) > 0) ||
                (pol[i].y <= y && y < pol[i].y && Tools2D.area2(pol[i], pol[j], p) > 0)) b = !b;
            j=i;
	}
	return b;
    }
}

class CvTetris extends Canvas
{
    int centerX, centerY;
    float pixelSize, rWidth = 700.0F, rHeight = 800.0F;
    Random generator = new Random();
    public static boolean flag = false;
    int randomIndex = generator.nextInt(7);
    int randomIndex2 = generator.nextInt(7);
    int randomIndex3 = generator.nextInt(7);
    boolean start = true;
    int[][] dl = new int[1000][4];
    int[][] db = new int[1000][4];
    int[][] dt = new int[1000][4];
    int[][] dr = new int[1000][4];
    int[][] fl = new int[1000][4];
    int[][] ft = new int[1000][4];
    int i = 0;
    boolean end = false;
    int[] color = new int[1000];
    boolean rt = false, lf = false;
    boolean clock;
    int notches = 0, status = 0;
    boolean[][] fill = new boolean[30][20];
    boolean[] line = new boolean[30];
    int erase;
    public static int M=5, lines=0, score=0, level=1;
    public static int N=5;
    int FS=400;
    public static float S=0.1F;
    boolean terminate = false;
    boolean loop = true;
    Point2D[] poly1 = new Point2D[4];
    Point2D[] poly2 = new Point2D[4];
    Point2D[] poly3 = new Point2D[4];
    Point2D[] poly4 = new Point2D[4];
    Point2D poly;
    boolean inPoly = false;
    public static int width = 10, height = 20;
    public static int left = -300, right = 50,    // integers to draw main area
                      bottom = -350, top = 350;

    CvTetris()
    {
        addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent evt)
            {
                int xA = evt.getX(), yA = evt.getY();

                if(SwingUtilities.isLeftMouseButton(evt))
                {
                    // if QUIT button is pressed
                    if(xA > iX(150) && xA < iX(250) && yA > iY(-300) && yA < iY(-330))
                    {
                        System.exit(0);
                    }
                    else
                    {
                        lf = true;
                        if(terminate == false)
                            repaint();
                    }
                }

                if(SwingUtilities.isRightMouseButton(evt))
                {
                    rt = true;
                    if(terminate == false)
                    	repaint();
                }
            }
        });

        addMouseWheelListener(new MouseAdapter()
        {
            public void mouseWheelMoved(MouseWheelEvent e)
            {
                notches = e.getWheelRotation();
            }
        });

        addMouseMotionListener(new MouseAdapter()
        {
            public void mouseMoved(MouseEvent evt)
            {
                int xB = evt.getX(), yB= evt.getY();

                // if mouse cursor is into the main area
                if(xB > iX(-301) && xB < iX(51) && yB > iY(351) && yB < iY(-351))
                    flag = true;
                else
                    flag = false;

		poly = new Point2D(fx(xB), fy(yB));

		if(Tools2D.insidePolygon(poly, poly1) || Tools2D.insidePolygon(poly, poly2)
                    || Tools2D.insidePolygon(poly, poly3) || Tools2D.insidePolygon(poly, poly4))
                    inPoly = true;

                if(terminate == false)
                    repaint();
            }
        });
    }

    void initgr()
    {
        Dimension d = getSize();
        int maxX = d.width - 1, maxY = d.height - 1;
        pixelSize = Math.max(rWidth/maxX, rHeight/maxY);
        centerX = maxX/2; centerY = maxY/2;
    }

    int iX(float x){return Math.round(centerX + x/pixelSize);}
    int iY(float y){return Math.round(centerY - y/pixelSize);}
    float fx(int x){return (x-centerX) * pixelSize;}
    float fy(int y){return (centerY-y) * pixelSize;}

    public void paint(Graphics g)
    {
        initgr();
        
        int mainLeft = left;
        int mainBottom = bottom;

        // draw main area
        g.drawRect(iX(left), iY(top), iX(right) - iX(left), iY(bottom) - iY(top));

        // draw next shape box
        g.drawLine(iX(140), iY(300), iX(310), iY(300));
        g.drawLine(iX(310), iY(300), iX(310), iY(200));
        g.drawLine(iX(310), iY(200), iX(140), iY(200));
        g.drawLine(iX(140), iY(200), iX(140), iY(300));

        g.setFont(new Font("Times Roman", Font.BOLD, Math.round(18/pixelSize)));
        g.drawString("Level:            " + level, iX(150), iY(100));
        g.drawString("Lines:            " + lines, iX(150), iY(50));
        g.drawString("Score:            " + score, iX(150), iY(0));

        // QUIT button
        g.drawLine(iX(150), iY(-300), iX(250), iY(-300));
        g.drawLine(iX(250), iY(-300), iX(250), iY(-330));
        g.drawLine(iX(250), iY(-330), iX(150), iY(-330));
        g.drawLine(iX(150), iY(-330), iX(150), iY(-300));
        g.drawString("QUIT", iX(180), iY(-323));

        while(randomIndex == randomIndex2)
        {
            randomIndex2 = generator.nextInt(7);
        }

        switch(randomIndex2)
        {
            case 0:

                g.drawRect(iX(240), iY(285), iX(275) - iX(240), iY(250) - iY(285));
                g.drawRect(iX(205), iY(285), iX(240) - iX(205), iY(250) - iY(285));
                g.drawRect(iX(205), iY(250), iX(240) - iX(205), iY(215) - iY(250));
                g.drawRect(iX(170), iY(250), iX(205) - iX(170), iY(215) - iY(250));
                g.setColor(Color.yellow);
                g.fillRect(iX(241), iY(284), iX(275) - iX(241), iY(250) - iY(284));
                g.fillRect(iX(206), iY(284), iX(240) - iX(206), iY(250) - iY(284));
                g.fillRect(iX(206), iY(249), iX(240) - iX(206), iY(215) - iY(249));
                g.fillRect(iX(171), iY(249), iX(205) - iX(171), iY(215) - iY(249));
                break;

            case 1:

                g.drawRect(iX(170), iY(285), iX(205) - iX(170), iY(250) - iY(285));
                g.drawRect(iX(205), iY(285), iX(240) - iX(205), iY(250) - iY(285));
                g.drawRect(iX(205), iY(250), iX(240) - iX(205), iY(215) - iY(250));
                g.drawRect(iX(240), iY(250), iX(275) - iX(240), iY(215) - iY(250));
                g.setColor(Color.magenta);
                g.fillRect(iX(171), iY(284), iX(205) - iX(171), iY(250) - iY(284));
                g.fillRect(iX(206), iY(284), iX(240) - iX(206), iY(250) - iY(284));
                g.fillRect(iX(206), iY(249), iX(240) - iX(206), iY(215) - iY(249));
                g.fillRect(iX(241), iY(249), iX(275) - iX(241), iY(215) - iY(249));
                break;

            case 2:

                g.drawRect(iX(170), iY(285), iX(205) - iX(170), iY(250) - iY(285));
                g.drawRect(iX(170), iY(250), iX(205) - iX(170), iY(215) - iY(250));
                g.drawRect(iX(205), iY(250), iX(240) - iX(205), iY(215) - iY(250));
                g.drawRect(iX(240), iY(250), iX(275) - iX(240), iY(215) - iY(250));
                g.setColor(Color.blue);
                g.fillRect(iX(171), iY(284), iX(205) - iX(171), iY(250) - iY(284));
                g.fillRect(iX(171), iY(249), iX(205) - iX(171), iY(215) - iY(249));
                g.fillRect(iX(206), iY(249), iX(240) - iX(206), iY(215) - iY(249));
                g.fillRect(iX(241), iY(249), iX(275) - iX(241), iY(215) - iY(249));
                break;

            case 3:

                g.drawRect(iX(240), iY(285), iX(275) - iX(240), iY(250) - iY(285));
                g.drawRect(iX(240), iY(250), iX(275) - iX(240), iY(215) - iY(250));
                g.drawRect(iX(205), iY(250), iX(240) - iX(205), iY(215) - iY(250));
                g.drawRect(iX(170), iY(250), iX(205) - iX(170), iY(215) - iY(250));
                g.setColor(Color.red);
                g.fillRect(iX(241), iY(284), iX(275) - iX(241), iY(250) - iY(284));
                g.fillRect(iX(241), iY(249), iX(275) - iX(241), iY(215) - iY(249));
                g.fillRect(iX(206), iY(249), iX(240) - iX(206), iY(215) - iY(249));
                g.fillRect(iX(171), iY(249), iX(205) - iX(171), iY(215) - iY(249));
                break;

            case 4:

                g.drawRect(iX(190), iY(285), iX(225) - iX(190), iY(250) - iY(285));
                g.drawRect(iX(225), iY(285), iX(260) - iX(225), iY(250) - iY(285));
                g.drawRect(iX(190), iY(250), iX(225) - iX(190), iY(215) - iY(250));
                g.drawRect(iX(225), iY(250), iX(260) - iX(225), iY(215) - iY(250));
                g.setColor(Color.green);
                g.fillRect(iX(191), iY(284), iX(225) - iX(191), iY(250) - iY(284));
                g.fillRect(iX(226), iY(284), iX(260) - iX(226), iY(250) - iY(284));
                g.fillRect(iX(191), iY(249), iX(225) - iX(191), iY(215) - iY(249));
                g.fillRect(iX(226), iY(249), iX(260) - iX(226), iY(215) - iY(249));
                break;

            case 5:

                g.drawRect(iX(205), iY(285), iX(240) - iX(205), iY(250) - iY(285));
                g.drawRect(iX(170), iY(250), iX(205) - iX(170), iY(215) - iY(250));
                g.drawRect(iX(205), iY(250), iX(240) - iX(205), iY(215) - iY(250));
                g.drawRect(iX(240), iY(250), iX(275) - iX(240), iY(215) - iY(250));
                g.setColor(Color.orange);
                g.fillRect(iX(206), iY(284), iX(240) - iX(206), iY(250) - iY(284));
                g.fillRect(iX(171), iY(249), iX(205) - iX(171), iY(215) - iY(249));
                g.fillRect(iX(206), iY(249), iX(240) - iX(206), iY(215) - iY(249));
                g.fillRect(iX(241), iY(249), iX(275) - iX(241), iY(215) - iY(249));
                break;

            case 6:

                g.drawRect(iX(155), iY(265), iX(190) - iX(155), iY(230) - iY(265));
                g.drawRect(iX(190), iY(265), iX(225) - iX(190), iY(230) - iY(265));
                g.drawRect(iX(225), iY(265), iX(260) - iX(225), iY(230) - iY(265));
                g.drawRect(iX(260), iY(265), iX(295) - iX(260), iY(230) - iY(265));
                g.setColor(Color.cyan);
                g.fillRect(iX(156), iY(264), iX(190) - iX(156), iY(230) - iY(264));
                g.fillRect(iX(191), iY(264), iX(225) - iX(191), iY(230) - iY(264));
                g.fillRect(iX(226), iY(264), iX(260) - iX(226), iY(230) - iY(264));
                g.fillRect(iX(261), iY(264), iX(295) - iX(261), iY(230) - iY(264));
                break;
        }

        g.setColor(Color.black);

        if(start==true && terminate == false)
        {
            i++;
            end = false;
            color[i] = randomIndex;
            status = 0;

            switch(randomIndex)
            {
                case 0:

                    dl[i][0]=-125; dt[i][0]=350; dr[i][0]=-90; db[i][0]=315;
                    dl[i][1]=-160; dt[i][1]=350; dr[i][1]=-125; db[i][1]=315;
                    dl[i][2]=-160; dt[i][2]=315; dr[i][2]=-125; db[i][2]=280;
                    dl[i][3]=-195; dt[i][3]=315; dr[i][3]=-160; db[i][3]=280;
                    fl[i][0]=dl[i][0]+1; ft[i][0]=dt[i][0]-1;
                    fl[i][1]=dl[i][1]+1; ft[i][1]=dt[i][1]-1;
                    fl[i][2]=dl[i][2]+1; ft[i][2]=dt[i][2]-1;
                    fl[i][3]=dl[i][3]+1; ft[i][3]=dt[i][3]-1;

                    g.drawRect(iX(dl[i][0]), iY(dt[i][0]), iX(dr[i][0]) - iX(dl[i][0]), iY(db[i][0]) - iY(dt[i][0]));
                    g.drawRect(iX(dl[i][1]), iY(dt[i][1]), iX(dr[i][1]) - iX(dl[i][1]), iY(db[i][1]) - iY(dt[i][1]));
                    g.drawRect(iX(dl[i][2]), iY(dt[i][2]), iX(dr[i][2]) - iX(dl[i][2]), iY(db[i][2]) - iY(dt[i][2]));
                    g.drawRect(iX(dl[i][3]), iY(dt[i][3]), iX(dr[i][3]) - iX(dl[i][3]), iY(db[i][3]) - iY(dt[i][3]));
                    g.setColor(Color.yellow);
                    g.fillRect(iX(fl[i][0]), iY(ft[i][0]), iX(dr[i][0]) - iX(fl[i][0]), iY(db[i][0]) - iY(ft[i][0]));
                    g.fillRect(iX(fl[i][1]), iY(ft[i][1]), iX(dr[i][1]) - iX(fl[i][1]), iY(db[i][1]) - iY(ft[i][1]));
                    g.fillRect(iX(fl[i][2]), iY(ft[i][2]), iX(dr[i][2]) - iX(fl[i][2]), iY(db[i][2]) - iY(ft[i][2]));
                    g.fillRect(iX(fl[i][3]), iY(ft[i][3]), iX(dr[i][3]) - iX(fl[i][3]), iY(db[i][3]) - iY(ft[i][3]));
                    break;

                case 1:

                    dl[i][0]=-195; dt[i][0]=350; dr[i][0]=-160; db[i][0]=315;
                    dl[i][1]=-160; dt[i][1]=350; dr[i][1]=-125; db[i][1]=315;
                    dl[i][2]=-160; dt[i][2]=315; dr[i][2]=-125; db[i][2]=280;
                    dl[i][3]=-125; dt[i][3]=315; dr[i][3]=-90; db[i][3]=280;
                    fl[i][0]=dl[i][0]+1; ft[i][0]=dt[i][0]-1;
                    fl[i][1]=dl[i][1]+1; ft[i][1]=dt[i][1]-1;
                    fl[i][2]=dl[i][2]+1; ft[i][2]=dt[i][2]-1;
                    fl[i][3]=dl[i][3]+1; ft[i][3]=dt[i][3]-1;

                    g.drawRect(iX(dl[i][0]), iY(dt[i][0]), iX(dr[i][0]) - iX(dl[i][0]), iY(db[i][0]) - iY(dt[i][0]));
                    g.drawRect(iX(dl[i][1]), iY(dt[i][1]), iX(dr[i][1]) - iX(dl[i][1]), iY(db[i][1]) - iY(dt[i][1]));
                    g.drawRect(iX(dl[i][2]), iY(dt[i][2]), iX(dr[i][2]) - iX(dl[i][2]), iY(db[i][2]) - iY(dt[i][2]));
                    g.drawRect(iX(dl[i][3]), iY(dt[i][3]), iX(dr[i][3]) - iX(dl[i][3]), iY(db[i][3]) - iY(dt[i][3]));
                    g.setColor(Color.magenta);
                    g.fillRect(iX(fl[i][0]), iY(ft[i][0]), iX(dr[i][0]) - iX(fl[i][0]), iY(db[i][0]) - iY(ft[i][0]));
                    g.fillRect(iX(fl[i][1]), iY(ft[i][1]), iX(dr[i][1]) - iX(fl[i][1]), iY(db[i][1]) - iY(ft[i][1]));
                    g.fillRect(iX(fl[i][2]), iY(ft[i][2]), iX(dr[i][2]) - iX(fl[i][2]), iY(db[i][2]) - iY(ft[i][2]));
                    g.fillRect(iX(fl[i][3]), iY(ft[i][3]), iX(dr[i][3]) - iX(fl[i][3]), iY(db[i][3]) - iY(ft[i][3]));
                    break;

                case 2:

                    dl[i][0]=-195; dt[i][0]=350; dr[i][0]=-160; db[i][0]=315;
                    dl[i][1]=-195; dt[i][1]=315; dr[i][1]=-160; db[i][1]=280;
                    dl[i][2]=-160; dt[i][2]=315; dr[i][2]=-125; db[i][2]=280;
                    dl[i][3]=-125; dt[i][3]=315; dr[i][3]=-90; db[i][3]=280;
                    fl[i][0]=dl[i][0]+1; ft[i][0]=dt[i][0]-1;
                    fl[i][1]=dl[i][1]+1; ft[i][1]=dt[i][1]-1;
                    fl[i][2]=dl[i][2]+1; ft[i][2]=dt[i][2]-1;
                    fl[i][3]=dl[i][3]+1; ft[i][3]=dt[i][3]-1;

                    g.drawRect(iX(dl[i][0]), iY(dt[i][0]), iX(dr[i][0]) - iX(dl[i][0]), iY(db[i][0]) - iY(dt[i][0]));
                    g.drawRect(iX(dl[i][1]), iY(dt[i][1]), iX(dr[i][1]) - iX(dl[i][1]), iY(db[i][1]) - iY(dt[i][1]));
                    g.drawRect(iX(dl[i][2]), iY(dt[i][2]), iX(dr[i][2]) - iX(dl[i][2]), iY(db[i][2]) - iY(dt[i][2]));
                    g.drawRect(iX(dl[i][3]), iY(dt[i][3]), iX(dr[i][3]) - iX(dl[i][3]), iY(db[i][3]) - iY(dt[i][3]));
                    g.setColor(Color.blue);
                    g.fillRect(iX(fl[i][0]), iY(ft[i][0]), iX(dr[i][0]) - iX(fl[i][0]), iY(db[i][0]) - iY(ft[i][0]));
                    g.fillRect(iX(fl[i][1]), iY(ft[i][1]), iX(dr[i][1]) - iX(fl[i][1]), iY(db[i][1]) - iY(ft[i][1]));
                    g.fillRect(iX(fl[i][2]), iY(ft[i][2]), iX(dr[i][2]) - iX(fl[i][2]), iY(db[i][2]) - iY(ft[i][2]));
                    g.fillRect(iX(fl[i][3]), iY(ft[i][3]), iX(dr[i][3]) - iX(fl[i][3]), iY(db[i][3]) - iY(ft[i][3]));
                    break;

                case 3:

                    dl[i][0]=-125; dt[i][0]=350; dr[i][0]=-90; db[i][0]=315;
                    dl[i][1]=-125; dt[i][1]=315; dr[i][1]=-90; db[i][1]=280;
                    dl[i][2]=-160; dt[i][2]=315; dr[i][2]=-125; db[i][2]=280;
                    dl[i][3]=-195; dt[i][3]=315; dr[i][3]=-160; db[i][3]=280;
                    fl[i][0]=dl[i][0]+1; ft[i][0]=dt[i][0]-1;
                    fl[i][1]=dl[i][1]+1; ft[i][1]=dt[i][1]-1;
                    fl[i][2]=dl[i][2]+1; ft[i][2]=dt[i][2]-1;
                    fl[i][3]=dl[i][3]+1; ft[i][3]=dt[i][3]-1;

                    g.drawRect(iX(dl[i][0]), iY(dt[i][0]), iX(dr[i][0]) - iX(dl[i][0]), iY(db[i][0]) - iY(dt[i][0]));
                    g.drawRect(iX(dl[i][1]), iY(dt[i][1]), iX(dr[i][1]) - iX(dl[i][1]), iY(db[i][1]) - iY(dt[i][1]));
                    g.drawRect(iX(dl[i][2]), iY(dt[i][2]), iX(dr[i][2]) - iX(dl[i][2]), iY(db[i][2]) - iY(dt[i][2]));
                    g.drawRect(iX(dl[i][3]), iY(dt[i][3]), iX(dr[i][3]) - iX(dl[i][3]), iY(db[i][3]) - iY(dt[i][3]));
                    g.setColor(Color.red);
                    g.fillRect(iX(fl[i][0]), iY(ft[i][0]), iX(dr[i][0]) - iX(fl[i][0]), iY(db[i][0]) - iY(ft[i][0]));
                    g.fillRect(iX(fl[i][1]), iY(ft[i][1]), iX(dr[i][1]) - iX(fl[i][1]), iY(db[i][1]) - iY(ft[i][1]));
                    g.fillRect(iX(fl[i][2]), iY(ft[i][2]), iX(dr[i][2]) - iX(fl[i][2]), iY(db[i][2]) - iY(ft[i][2]));
                    g.fillRect(iX(fl[i][3]), iY(ft[i][3]), iX(dr[i][3]) - iX(fl[i][3]), iY(db[i][3]) - iY(ft[i][3]));
                    break;

                case 4:

                    dl[i][0]=-160; dt[i][0]=350; dr[i][0]=-125; db[i][0]=315;
                    dl[i][1]=-160; dt[i][1]=315; dr[i][1]=-125; db[i][1]=280;
                    dl[i][2]=-125; dt[i][2]=350; dr[i][2]=-90; db[i][2]=315;
                    dl[i][3]=-125; dt[i][3]=315; dr[i][3]=-90; db[i][3]=280;
                    fl[i][0]=dl[i][0]+1; ft[i][0]=dt[i][0]-1;
                    fl[i][1]=dl[i][1]+1; ft[i][1]=dt[i][1]-1;
                    fl[i][2]=dl[i][2]+1; ft[i][2]=dt[i][2]-1;
                    fl[i][3]=dl[i][3]+1; ft[i][3]=dt[i][3]-1;

                    g.drawRect(iX(dl[i][0]), iY(dt[i][0]), iX(dr[i][0]) - iX(dl[i][0]), iY(db[i][0]) - iY(dt[i][0]));
                    g.drawRect(iX(dl[i][1]), iY(dt[i][1]), iX(dr[i][1]) - iX(dl[i][1]), iY(db[i][1]) - iY(dt[i][1]));
                    g.drawRect(iX(dl[i][2]), iY(dt[i][2]), iX(dr[i][2]) - iX(dl[i][2]), iY(db[i][2]) - iY(dt[i][2]));
                    g.drawRect(iX(dl[i][3]), iY(dt[i][3]), iX(dr[i][3]) - iX(dl[i][3]), iY(db[i][3]) - iY(dt[i][3]));
                    g.setColor(Color.green);
                    g.fillRect(iX(fl[i][0]), iY(ft[i][0]), iX(dr[i][0]) - iX(fl[i][0]), iY(db[i][0]) - iY(ft[i][0]));
                    g.fillRect(iX(fl[i][1]), iY(ft[i][1]), iX(dr[i][1]) - iX(fl[i][1]), iY(db[i][1]) - iY(ft[i][1]));
                    g.fillRect(iX(fl[i][2]), iY(ft[i][2]), iX(dr[i][2]) - iX(fl[i][2]), iY(db[i][2]) - iY(ft[i][2]));
                    g.fillRect(iX(fl[i][3]), iY(ft[i][3]), iX(dr[i][3]) - iX(fl[i][3]), iY(db[i][3]) - iY(ft[i][3]));
                    break;

                case 5:

                    dl[i][0]=-160; dt[i][0]=350; dr[i][0]=-125; db[i][0]=315;
                    dl[i][1]=-195; dt[i][1]=315; dr[i][1]=-160; db[i][1]=280;
                    dl[i][2]=-160; dt[i][2]=315; dr[i][2]=-125; db[i][2]=280;
                    dl[i][3]=-125; dt[i][3]=315; dr[i][3]=-90; db[i][3]=280;
                    fl[i][0]=dl[i][0]+1; ft[i][0]=dt[i][0]-1;
                    fl[i][1]=dl[i][1]+1; ft[i][1]=dt[i][1]-1;
                    fl[i][2]=dl[i][2]+1; ft[i][2]=dt[i][2]-1;
                    fl[i][3]=dl[i][3]+1; ft[i][3]=dt[i][3]-1;

                    g.drawRect(iX(dl[i][0]), iY(dt[i][0]), iX(dr[i][0]) - iX(dl[i][0]), iY(db[i][0]) - iY(dt[i][0]));
                    g.drawRect(iX(dl[i][1]), iY(dt[i][1]), iX(dr[i][1]) - iX(dl[i][1]), iY(db[i][1]) - iY(dt[i][1]));
                    g.drawRect(iX(dl[i][2]), iY(dt[i][2]), iX(dr[i][2]) - iX(dl[i][2]), iY(db[i][2]) - iY(dt[i][2]));
                    g.drawRect(iX(dl[i][3]), iY(dt[i][3]), iX(dr[i][3]) - iX(dl[i][3]), iY(db[i][3]) - iY(dt[i][3]));
                    g.setColor(Color.orange);
                    g.fillRect(iX(fl[i][0]), iY(ft[i][0]), iX(dr[i][0]) - iX(fl[i][0]), iY(db[i][0]) - iY(ft[i][0]));
                    g.fillRect(iX(fl[i][1]), iY(ft[i][1]), iX(dr[i][1]) - iX(fl[i][1]), iY(db[i][1]) - iY(ft[i][1]));
                    g.fillRect(iX(fl[i][2]), iY(ft[i][2]), iX(dr[i][2]) - iX(fl[i][2]), iY(db[i][2]) - iY(ft[i][2]));
                    g.fillRect(iX(fl[i][3]), iY(ft[i][3]), iX(dr[i][3]) - iX(fl[i][3]), iY(db[i][3]) - iY(ft[i][3]));
                    break;

                case 6:

                    dl[i][0]=-195; dt[i][0]=350; dr[i][0]=-160; db[i][0]=315;
                    dl[i][1]=-160; dt[i][1]=350; dr[i][1]=-125; db[i][1]=315;
                    dl[i][2]=-125; dt[i][2]=350; dr[i][2]=-90; db[i][2]=315;
                    dl[i][3]=-90; dt[i][3]=350; dr[i][3]=-55; db[i][3]=315;
                    fl[i][0]=dl[i][0]+1; ft[i][0]=dt[i][0]-1;
                    fl[i][1]=dl[i][1]+1; ft[i][1]=dt[i][1]-1;
                    fl[i][2]=dl[i][2]+1; ft[i][2]=dt[i][2]-1;
                    fl[i][3]=dl[i][3]+1; ft[i][3]=dt[i][3]-1;

                    g.drawRect(iX(dl[i][0]), iY(dt[i][0]), iX(dr[i][0]) - iX(dl[i][0]), iY(db[i][0]) - iY(dt[i][0]));
                    g.drawRect(iX(dl[i][1]), iY(dt[i][1]), iX(dr[i][1]) - iX(dl[i][1]), iY(db[i][1]) - iY(dt[i][1]));
                    g.drawRect(iX(dl[i][2]), iY(dt[i][2]), iX(dr[i][2]) - iX(dl[i][2]), iY(db[i][2]) - iY(dt[i][2]));
                    g.drawRect(iX(dl[i][3]), iY(dt[i][3]), iX(dr[i][3]) - iX(dl[i][3]), iY(db[i][3]) - iY(dt[i][3]));
                    g.setColor(Color.cyan);
                    g.fillRect(iX(fl[i][0]), iY(ft[i][0]), iX(dr[i][0]) - iX(fl[i][0]), iY(db[i][0]) - iY(ft[i][0]));
                    g.fillRect(iX(fl[i][1]), iY(ft[i][1]), iX(dr[i][1]) - iX(fl[i][1]), iY(db[i][1]) - iY(ft[i][1]));
                    g.fillRect(iX(fl[i][2]), iY(ft[i][2]), iX(dr[i][2]) - iX(fl[i][2]), iY(db[i][2]) - iY(ft[i][2]));
                    g.fillRect(iX(fl[i][3]), iY(ft[i][3]), iX(dr[i][3]) - iX(fl[i][3]), iY(db[i][3]) - iY(ft[i][3]));
                    break;
            }
        }

        // forward
        if(notches < 0 && flag == false)
        {
            if(status == 1 && clock == true)
                status = status + 2;
            if(status == 3 && clock == true)
                status = status - 2;

            switch(randomIndex)
            {
                case 0:
                    status = (status + 1) % 2;
                    switch(status)
                    {
                        case 0:
                            dl[i][0]+=35; dr[i][0]+=35; dt[i][0]-=35; db[i][0]-=35; fl[i][0]+=35; ft[i][0]-=35;
                            dl[i][2]-=35; dr[i][2]-=35; dt[i][2]-=35; db[i][2]-=35; fl[i][2]-=35; ft[i][2]-=35;
                            dl[i][3]-=70; dr[i][3]-=70; fl[i][3]-=70;
                            break;
                        case 1:
                            dl[i][0]-=35; dr[i][0]-=35; dt[i][0]+=35; db[i][0]+=35; fl[i][0]-=35; ft[i][0]+=35;
                            dl[i][2]+=35; dr[i][2]+=35; dt[i][2]+=35; db[i][2]+=35; fl[i][2]+=35; ft[i][2]+=35;
                            dl[i][3]+=70; dr[i][3]+=70; fl[i][3]+=70;
                            break;
                    }
                    break;

                case 1:
                    status = (status + 1) % 2;
                    switch(status)
                    {
                        case 0:
                            dl[i][0]-=35; dr[i][0]-=35; dt[i][0]-=35; db[i][0]-=35; fl[i][0]-=35; ft[i][0]-=35;
                            dl[i][2]+=35; dr[i][2]+=35; dt[i][2]-=35; db[i][2]-=35; fl[i][2]+=35; ft[i][2]-=35;
                            dl[i][3]+=70; dr[i][3]+=70; fl[i][3]+=70;
                            break;
                        case 1:
                            dl[i][0]+=35; dr[i][0]+=35; dt[i][0]+=35; db[i][0]+=35; fl[i][0]+=35; ft[i][0]+=35;
                            dl[i][2]-=35; dr[i][2]-=35; dt[i][2]+=35; db[i][2]+=35; fl[i][2]-=35; ft[i][2]+=35;
                            dl[i][3]-=70; dr[i][3]-=70; fl[i][3]-=70;
                            break;
                    }
                    break;

                case 2:
                    status = (status + 1) % 4;
                    switch(status)
                    {
                        case 0:
                            dl[i][0]-=35; dr[i][0]-=35; dt[i][0]+=35; db[i][0]+=35; fl[i][0]-=35; ft[i][0]+=35;
                            dl[i][1]-=70; dr[i][1]-=70; fl[i][1]-=70;
                            dl[i][2]-=35; dr[i][2]-=35; dt[i][2]-=35; db[i][2]-=35; fl[i][2]-=35; ft[i][2]-=35;
                            dt[i][3]-=70; db[i][3]-=70; ft[i][3]-=70;
                            break;
                        case 1:
                            dl[i][0]+=35; dr[i][0]+=35; dt[i][0]+=35; db[i][0]+=35; fl[i][0]+=35; ft[i][0]+=35;
                            dt[i][1]+=70; db[i][1]+=70; ft[i][1]+=70;
                            dl[i][2]-=35; dr[i][2]-=35; dt[i][2]+=35; db[i][2]+=35; fl[i][2]-=35; ft[i][2]+=35;
                            dl[i][3]-=70; dr[i][3]-=70; fl[i][3]-=70;
                            break;
                        case 2:
                            dl[i][0]+=35; dr[i][0]+=35; dt[i][0]-=70; db[i][0]-=70; fl[i][0]+=35; ft[i][0]-=70;
                            dl[i][1]+=70; dr[i][1]+=70; dt[i][1]-=35; db[i][1]-=35; fl[i][1]+=70; ft[i][1]-=35;
                            dl[i][2]+=35; dr[i][2]+=35; fl[i][2]+=35;
                            dt[i][3]+=35; db[i][3]+=35; ft[i][3]+=35;
                            break;
                        case 3:
                            dl[i][0]-=35; dr[i][0]-=35; fl[i][0]-=35;
                            dt[i][1]-=35; db[i][1]-=35; ft[i][1]-=35;
                            dl[i][2]+=35; dr[i][2]+=35; fl[i][2]+=35;
                            dl[i][3]+=70; dr[i][3]+=70; dt[i][3]+=35; db[i][3]+=35; fl[i][3]+=70; ft[i][3]+=35;
                            break;
                    }
                    break;

                case 3:
                    status = (status + 1) % 4;
                    switch(status)
                    {
                       case 0:
                            dl[i][0]+=35; dr[i][0]+=35; dt[i][0]-=35; db[i][0]-=35; fl[i][0]+=35; ft[i][0]-=35;
                            dt[i][1]-=70; db[i][1]-=70; ft[i][1]-=70;
                            dl[i][2]-=35; dr[i][2]-=35; dt[i][2]-=35; db[i][2]-=35; fl[i][2]-=35; ft[i][2]-=35;
                            dl[i][3]-=70; dr[i][3]-=70; fl[i][3]-=70;
                            break;
                        case 1:
                            dl[i][0]-=35; dr[i][0]-=35; dt[i][0]-=35; db[i][0]-=35; fl[i][0]-=35; ft[i][0]-=35;
                            dl[i][1]-=70; dr[i][1]-=70; fl[i][1]-=70;
                            dl[i][2]-=35; dr[i][2]-=35; dt[i][2]+=35; db[i][2]+=35; fl[i][2]-=35; ft[i][2]+=35;
                            dt[i][3]+=70; db[i][3]+=70; ft[i][3]+=70;
                            break;
                        case 2:
                            dl[i][0]-=35; dr[i][0]-=35; fl[i][0]-=35;
                            dt[i][1]+=35; db[i][1]+=35; ft[i][1]+=35;
                            dl[i][2]+=35; dr[i][2]+=35; fl[i][2]+=35;
                            dl[i][3]+=70; dr[i][3]+=70; dt[i][3]-=35; db[i][3]-=35; fl[i][3]+=70; ft[i][3]-=35;
                            break;
                        case 3:
                            dl[i][0]+=35; dr[i][0]+=35; dt[i][0]+=70; db[i][0]+=70; fl[i][0]+=35; ft[i][0]+=70;
                            dl[i][1]+=70; dr[i][1]+=70; dt[i][1]+=35; db[i][1]+=35; fl[i][1]+=70; ft[i][1]+=35;
                            dl[i][2]+=35; dr[i][2]+=35; fl[i][2]+=35;
                            dt[i][3]-=35; db[i][3]-=35; ft[i][3]-=35;
                            break;
                    }
                    break;

                case 4:
                    break;

                case 5:
                    status = (status + 1) % 4;
                    switch(status)
                    {
                        case 0:
                            dl[i][0]-=35; dr[i][0]-=35; dt[i][0]-=35; db[i][0]-=35; fl[i][0]-=35; ft[i][0]-=35;
                            dl[i][2]+=35; dr[i][2]+=35; dt[i][2]+=35; db[i][2]+=35; fl[i][2]+=35; ft[i][2]+=35;
                            dl[i][3]+=35; dr[i][3]+=35; dt[i][3]+=35; db[i][3]+=35; fl[i][3]+=35; ft[i][3]+=35;
                            break;
                        case 1:
                            dl[i][1]+=35; dr[i][1]+=35; fl[i][1]+=35;
                            dt[i][2]-=35; db[i][2]-=35; ft[i][2]-=35;
                            break;
                        case 2:
                            dl[i][0]-=35; dr[i][0]-=35; dt[i][0]-=35; db[i][0]-=35; fl[i][0]-=35; ft[i][0]-=35;
                            dl[i][2]+=35; dr[i][2]+=35; dt[i][2]+=35; db[i][2]+=35; fl[i][2]+=35; ft[i][2]+=35;
                            dl[i][3]-=35; dr[i][3]-=35; dt[i][3]-=35; db[i][3]-=35; fl[i][3]-=35; ft[i][3]-=35;
                            break;
                        case 3:
                            dl[i][0]+=35; dr[i][0]+=35; dt[i][0]+=35; db[i][0]+=35; fl[i][0]+=35; ft[i][0]+=35;
                            dl[i][2]-=35; dr[i][2]-=35; dt[i][2]-=35; db[i][2]-=35; fl[i][2]-=35; ft[i][2]-=35;
                            dl[i][3]-=35; dr[i][3]-=35; dt[i][3]+=35; db[i][3]+=35; fl[i][3]-=35; ft[i][3]+=35;
                            break;
                    }
                    break;

                case 6:
                    status = (status + 1) % 2;
                    switch(status)
                    {
                        case 0:
                            dl[i][0]-=35; dr[i][0]-=35; dt[i][0]-=35; db[i][0]-=35; fl[i][0]-=35; ft[i][0]-=35;
                            dl[i][2]+=35; dr[i][2]+=35; dt[i][2]+=35; db[i][2]+=35; fl[i][2]+=35; ft[i][2]+=35;
                            dl[i][3]+=70; dr[i][3]+=70; dt[i][3]+=70; db[i][3]+=70; fl[i][3]+=70; ft[i][3]+=70;
                            break;
                        case 1:
                            dl[i][0]+=35; dr[i][0]+=35; dt[i][0]+=35; db[i][0]+=35; fl[i][0]+=35; ft[i][0]+=35;
                            dl[i][2]-=35; dr[i][2]-=35; dt[i][2]-=35; db[i][2]-=35; fl[i][2]-=35; ft[i][2]-=35;
                            dl[i][3]-=70; dr[i][3]-=70; dt[i][3]-=70; db[i][3]-=70; fl[i][3]-=70; ft[i][3]-=70;
                            break;
                    }
                    break;
            }
            clock = false;
        }

        // backward
        if(notches > 0 && flag == false)
        {
            if(status == 1 && clock == false)
                status = status + 2;
            if(status == 3 && clock == false)
                status = status - 2;

            switch(randomIndex)
            {
                case 0:
                    status = (status + 1) % 2;
                    switch(status)
                    {
                        case 0:
                            dl[i][0]+=35; dr[i][0]+=35; dt[i][0]-=35; db[i][0]-=35; fl[i][0]+=35; ft[i][0]-=35;
                            dl[i][2]-=35; dr[i][2]-=35; dt[i][2]-=35; db[i][2]-=35; fl[i][2]-=35; ft[i][2]-=35;
                            dl[i][3]-=70; dr[i][3]-=70; fl[i][3]-=70;
                            break;
                        case 1:
                            dl[i][0]-=35; dr[i][0]-=35; dt[i][0]+=35; db[i][0]+=35; fl[i][0]-=35; ft[i][0]+=35;
                            dl[i][2]+=35; dr[i][2]+=35; dt[i][2]+=35; db[i][2]+=35; fl[i][2]+=35; ft[i][2]+=35;
                            dl[i][3]+=70; dr[i][3]+=70; fl[i][3]+=70;
                            break;
                    }
                    break;

                case 1:
                    status = (status + 1) % 2;
                    switch(status)
                    {
                        case 0:
                            dl[i][0]-=35; dr[i][0]-=35; dt[i][0]-=35; db[i][0]-=35; fl[i][0]-=35; ft[i][0]-=35;
                            dl[i][2]+=35; dr[i][2]+=35; dt[i][2]-=35; db[i][2]-=35; fl[i][2]+=35; ft[i][2]-=35;
                            dl[i][3]+=70; dr[i][3]+=70; fl[i][3]+=70;
                            break;
                        case 1:
                            dl[i][0]+=35; dr[i][0]+=35; dt[i][0]+=35; db[i][0]+=35; fl[i][0]+=35; ft[i][0]+=35;
                            dl[i][2]-=35; dr[i][2]-=35; dt[i][2]+=35; db[i][2]+=35; fl[i][2]-=35; ft[i][2]+=35;
                            dl[i][3]-=70; dr[i][3]-=70; fl[i][3]-=70;
                            break;
                    }
                    break;

                case 2:
                    status = (status + 1) % 4;
                    switch(status)
                    {
                        case 0:
                            dl[i][0]-=35; dr[i][0]-=35; dt[i][0]-=35; db[i][0]-=35; fl[i][0]-=35; ft[i][0]-=35;
                            dt[i][1]-=70; db[i][1]-=70; ft[i][1]-=70;
                            dl[i][2]+=35; dr[i][2]+=35; dt[i][2]-=35; db[i][2]-=35; fl[i][2]+=35; ft[i][2]-=35;
                            dl[i][3]+=70; dr[i][3]+=70; fl[i][3]+=70;
                            break;
                        case 1:
                            dl[i][0]+=35; dr[i][0]+=35; dt[i][0]-=35; db[i][0]-=35; fl[i][0]+=35; ft[i][0]-=35;
                            dl[i][1]+=70; dr[i][1]+=70; fl[i][1]+=70;
                            dl[i][2]+=35; dr[i][2]+=35; dt[i][2]+=35; db[i][2]+=35; fl[i][2]+=35; ft[i][2]+=35;
                            dt[i][3]+=70; db[i][3]+=70; ft[i][3]+=70;
                            break;
                        case 2:
                            dl[i][0]+=35; dr[i][0]+=35; fl[i][0]+=35;
                            dt[i][1]+=35; db[i][1]+=35; ft[i][1]+=35;
                            dl[i][2]-=35; dr[i][2]-=35; fl[i][2]-=35;
                            dl[i][3]-=70; dr[i][3]-=70; dt[i][3]-=35; db[i][3]-=35; fl[i][3]-=70; ft[i][3]-=35;
                            break;
                        case 3:
                            dl[i][0]-=35; dr[i][0]-=35; dt[i][0]+=70; db[i][0]+=70; fl[i][0]-=35; ft[i][0]+=70;
                            dl[i][1]-=70; dr[i][1]-=70; dt[i][1]+=35; db[i][1]+=35; fl[i][1]-=70; ft[i][1]+=35;
                            dl[i][2]-=35; dr[i][2]-=35; fl[i][2]-=35;
                            dt[i][3]-=35; db[i][3]-=35; ft[i][3]-=35;
                            break;
                    }
                    break;

                case 3:
                    status = (status + 1) % 4;
                    switch(status)
                    {
                        case 0:
                            dl[i][0]+=35; dr[i][0]+=35; dt[i][0]+=35; db[i][0]+=35; fl[i][0]+=35; ft[i][0]+=35;
                            dl[i][1]+=70; dr[i][1]+=70; fl[i][1]+=70;
                            dl[i][2]+=35; dr[i][2]+=35; dt[i][2]-=35; db[i][2]-=35; fl[i][2]+=35; ft[i][2]-=35;
                            dt[i][3]-=70; db[i][3]-=70; ft[i][3]-=70;
                            break;
                        case 1:
                            dl[i][0]-=35; dr[i][0]-=35; dt[i][0]+=35; db[i][0]+=35; fl[i][0]-=35; ft[i][0]+=35;
                            dt[i][1]+=70; db[i][1]+=70; ft[i][1]+=70;
                            dl[i][2]+=35; dr[i][2]+=35; dt[i][2]+=35; db[i][2]+=35; fl[i][2]+=35; ft[i][2]+=35;
                            dl[i][3]+=70; dr[i][3]+=70; fl[i][3]+=70;
                            break;
                        case 2:
                            dl[i][0]-=35; dr[i][0]-=35; dt[i][0]-=70; db[i][0]-=70; fl[i][0]-=35; ft[i][0]-=70;
                            dl[i][1]-=70; dr[i][1]-=70; dt[i][1]-=35; db[i][1]-=35; fl[i][1]-=70; ft[i][1]-=35;
                            dl[i][2]-=35; dr[i][2]-=35; fl[i][2]-=35;
                            dt[i][3]+=35; db[i][3]+=35; ft[i][3]+=35;
                            break;
                        case 3:
                            dl[i][0]+=35; dr[i][0]+=35; fl[i][0]+=35;
                            dt[i][1]-=35; db[i][1]-=35; ft[i][1]-=35;
                            dl[i][2]-=35; dr[i][2]-=35; fl[i][2]-=35;
                            dl[i][3]-=70; dr[i][3]-=70; dt[i][3]+=35; db[i][3]+=35; fl[i][3]-=70; ft[i][3]+=35;
                            break;
                    }
                    break;

                case 4:
                    break;

                case 5:
                    status = (status + 1) % 4;
                    switch(status)
                    {
                        case 0:
                            dl[i][1]-=35; dr[i][1]-=35; fl[i][1]-=35;
                            dt[i][2]+=35; db[i][2]+=35; ft[i][2]+=35;
                            break;
                        case 1:
                            dl[i][1]+=35; dr[i][1]+=35; fl[i][1]+=35;
                            dt[i][2]-=35; db[i][2]-=35; ft[i][2]-=35;
                            dl[i][3]-=70; dr[i][3]-=70; fl[i][3]-=70;
                            break;
                        case 2:
                            dl[i][0]-=35; dr[i][0]-=35; dt[i][0]-=35; db[i][0]-=35; fl[i][0]-=35; ft[i][0]-=35;
                            dl[i][2]+=35; dr[i][2]+=35; dt[i][2]+=35; db[i][2]+=35; fl[i][2]+=35; ft[i][2]+=35;
                            dl[i][3]+=35; dr[i][3]+=35; dt[i][3]-=35; db[i][3]-=35; fl[i][3]+=35; ft[i][3]-=35;
                            break;
                        case 3:
                            dl[i][0]+=35; dr[i][0]+=35; dt[i][0]+=35; db[i][0]+=35; fl[i][0]+=35; ft[i][0]+=35;
                            dl[i][2]-=35; dr[i][2]-=35; dt[i][2]-=35; db[i][2]-=35; fl[i][2]-=35; ft[i][2]-=35;
                            dl[i][3]+=35; dr[i][3]+=35; dt[i][3]+=35; db[i][3]+=35; fl[i][3]+=35; ft[i][3]+=35;
                            break;
                    }
                    break;

                case 6:
                    status = (status + 1) % 2;
                    switch(status)
                    {
                        case 0:
                            dl[i][0]-=35; dr[i][0]-=35; dt[i][0]-=35; db[i][0]-=35; fl[i][0]-=35; ft[i][0]-=35;
                            dl[i][2]+=35; dr[i][2]+=35; dt[i][2]+=35; db[i][2]+=35; fl[i][2]+=35; ft[i][2]+=35;
                            dl[i][3]+=70; dr[i][3]+=70; dt[i][3]+=70; db[i][3]+=70; fl[i][3]+=70; ft[i][3]+=70;
                            break;
                        case 1:
                            dl[i][0]+=35; dr[i][0]+=35; dt[i][0]+=35; db[i][0]+=35; fl[i][0]+=35; ft[i][0]+=35;
                            dl[i][2]-=35; dr[i][2]-=35; dt[i][2]-=35; db[i][2]-=35; fl[i][2]-=35; ft[i][2]-=35;
                            dl[i][3]-=70; dr[i][3]-=70; dt[i][3]-=70; db[i][3]-=70; fl[i][3]-=70; ft[i][3]-=70;
                            break;
                    }
                    break;
            }
            clock = true;
        }

        notches = 0;

        if(lf && dl[i][0]>left && dl[i][1]>left && dl[i][2]>left && dl[i][3]>left && flag == false)
        {
            dl[i][0]-=35; dr[i][0]-=35; dl[i][1]-=35; dr[i][1]-=35;
            dl[i][2]-=35; dr[i][2]-=35; dl[i][3]-=35; dr[i][3]-=35;
            fl[i][0]-=35; fl[i][1]-=35; fl[i][2]-=35; fl[i][3]-=35;
            lf = false;
        }

        if(rt && dr[i][0]<right && dr[i][1]<right && dr[i][2]<right && dr[i][3]<right && flag == false)
        {
            dl[i][0]+=35; dr[i][0]+=35; dl[i][1]+=35; dr[i][1]+=35;
            dl[i][2]+=35; dr[i][2]+=35; dl[i][3]+=35; dr[i][3]+=35;
            fl[i][0]+=35; fl[i][1]+=35; fl[i][2]+=35; fl[i][3]+=35;
            rt = false;
        }

        if(db[i][0]!=bottom && db[i][1]!=bottom && db[i][2]!=bottom && db[i][3]!=bottom && start == false && flag == false)
        {
            dt[i][0]-=35; db[i][0]-=35; dt[i][1]-=35; db[i][1]-=35;
            dt[i][2]-=35; db[i][2]-=35; dt[i][3]-=35; db[i][3]-=35;
            ft[i][0]-=35; ft[i][1]-=35; ft[i][2]-=35; ft[i][3]-=35;
        }

        int j, k, l, m;
        for(j=0; j<i; j++)
        {
            for(k=0; k<4; k++)
            {
                for(l=0; l<4; l++)
                {
                    if((dl[j][k] == dl[i][l]) && (dt[j][k] == db[i][l]))
                        end = true;
                }
            }
        }

        for(j=0; j<i; j++)
        {
            for(k=0; k<4; k++)
            {
                for(l=0; l<4; l++)
                {
                    if(dt[j][k] == top)
                    {
                     	terminate = true;
                     	start = false;
                    }
                }
            }
        }

	loop = true;

	while(loop)
	{
            loop = false;
            for(j=0; j<height; j++)
            {
        	for(k=0; k<width; k++)
        	{
                    fill[j][k] = false;
        	}
            }

            for(j=0; j<i; j++)
            {
        	for(k=0; k<4; k++)
        	{
                    for(l=0; l<height; l++)
                    {
        		for(m=0; m<width; m++)
        		{
                            if(dl[j][k] == mainLeft && db[j][k] == mainBottom)
        			fill[l][m] = true;
                            mainLeft += 35;
                        }
                        mainLeft = left;
                        mainBottom += 35;
                    }
                    mainBottom = bottom;
        	}
            }

            for(j=0; j<height; j++)
        	line[j] = true;

            for(j=0; j<height; j++)
            {
        	for(k=0; k<width; k++)
        	{
                    if(fill[j][k] == false)
                    {
        		line[j] = false;
                        break;
                    }
        	}
        	if(line[j] == true)
        	{
        	    for(l=j+1; l<height; l++)
                        line[l] = false;
        		
                    break;
        	}
            }

            for(j=0; j<height; j++)
            {
        	if(line[j] == true)
        	{
                    erase = (bottom) + 35*j;
                    for(k=0; k<i; k++)
                    {
        		for(l=0; l<4; l++)
        		{
                            if(db[k][l]==erase)
                            {dl[k][l]=0; dr[k][l]=0; dt[k][l]=-400; db[k][l]=-400; fl[k][l]=0; ft[k][l]=-400;}
                            if(db[k][l]>erase)
                            {dt[k][l]-=35; db[k][l]-=35; ft[k][l]-=35;}
        		}
                    }
                    lines += 1;
                    score += level * M;
                    if((lines % N)==0)
                    {
                        FS *= (1 - level * S);
                        level += 1;
                    }
                    loop = true;
                    break;
        	}
            }
	}

        if(i!=0)
        {
            for(j=0; j<i; j++)
            {
                g.setColor(Color.BLACK);
                g.drawRect(iX(dl[j][0]), iY(dt[j][0]), iX(dr[j][0]) - iX(dl[j][0]), iY(db[j][0]) - iY(dt[j][0]));
                g.drawRect(iX(dl[j][1]), iY(dt[j][1]), iX(dr[j][1]) - iX(dl[j][1]), iY(db[j][1]) - iY(dt[j][1]));
                g.drawRect(iX(dl[j][2]), iY(dt[j][2]), iX(dr[j][2]) - iX(dl[j][2]), iY(db[j][2]) - iY(dt[j][2]));
                g.drawRect(iX(dl[j][3]), iY(dt[j][3]), iX(dr[j][3]) - iX(dl[j][3]), iY(db[j][3]) - iY(dt[j][3]));
                switch(color[j])
                {
                    case 0:
                        g.setColor(Color.yellow);
                        break;
                    case 1:
                        g.setColor(Color.magenta);
                        break;
                    case 2:
                        g.setColor(Color.blue);
                        break;
                    case 3:
                        g.setColor(Color.red);
                        break;
                    case 4:
                        g.setColor(Color.green);
                        break;
                    case 5:
                        g.setColor(Color.orange);
                        break;
                    case 6:
                        g.setColor(Color.cyan);
                        break;
                }

                g.fillRect(iX(fl[j][0]), iY(ft[j][0]), iX(dr[j][0]) - iX(fl[j][0]), iY(db[j][0]) - iY(ft[j][0]));
                g.fillRect(iX(fl[j][1]), iY(ft[j][1]), iX(dr[j][1]) - iX(fl[j][1]), iY(db[j][1]) - iY(ft[j][1]));
                g.fillRect(iX(fl[j][2]), iY(ft[j][2]), iX(dr[j][2]) - iX(fl[j][2]), iY(db[j][2]) - iY(ft[j][2]));
                g.fillRect(iX(fl[j][3]), iY(ft[j][3]), iX(dr[j][3]) - iX(fl[j][3]), iY(db[j][3]) - iY(ft[j][3]));
            }
        }

        if(db[i][0]>=bottom && db[i][1]>=bottom && db[i][2]>=bottom && db[i][3]>=bottom && start == false && end == false && terminate == false)
        {
            g.setColor(Color.black);
            g.drawRect(iX(dl[i][0]), iY(dt[i][0]), iX(dr[i][0]) - iX(dl[i][0]), iY(db[i][0]) - iY(dt[i][0]));
            g.drawRect(iX(dl[i][1]), iY(dt[i][1]), iX(dr[i][1]) - iX(dl[i][1]), iY(db[i][1]) - iY(dt[i][1]));
            g.drawRect(iX(dl[i][2]), iY(dt[i][2]), iX(dr[i][2]) - iX(dl[i][2]), iY(db[i][2]) - iY(dt[i][2]));
            g.drawRect(iX(dl[i][3]), iY(dt[i][3]), iX(dr[i][3]) - iX(dl[i][3]), iY(db[i][3]) - iY(dt[i][3]));

            switch(randomIndex)
            {
                case 0:
                    g.setColor(Color.yellow);
                    break;
                case 1:
                    g.setColor(Color.magenta);
                    break;
                case 2:
                    g.setColor(Color.blue);
                    break;
                case 3:
                    g.setColor(Color.red);
                    break;
                case 4:
                    g.setColor(Color.green);
                    break;
                case 5:
                    g.setColor(Color.orange);
                    break;
                case 6:
                    g.setColor(Color.cyan);
                    break;
            }

            g.fillRect(iX(fl[i][0]), iY(ft[i][0]), iX(dr[i][0]) - iX(fl[i][0]), iY(db[i][0]) - iY(ft[i][0]));
            g.fillRect(iX(fl[i][1]), iY(ft[i][1]), iX(dr[i][1]) - iX(fl[i][1]), iY(db[i][1]) - iY(ft[i][1]));
            g.fillRect(iX(fl[i][2]), iY(ft[i][2]), iX(dr[i][2]) - iX(fl[i][2]), iY(db[i][2]) - iY(ft[i][2]));
            g.fillRect(iX(fl[i][3]), iY(ft[i][3]), iX(dr[i][3]) - iX(fl[i][3]), iY(db[i][3]) - iY(ft[i][3]));
        }

        start = false;

        if(db[i][0]==bottom || db[i][1]==bottom || db[i][2]==bottom || db[i][3]==bottom || end == true)
        {
            start = true;
            randomIndex = randomIndex2;
            randomIndex2 = generator.nextInt(7);
        }

        // if mouse cursor is into the main area, PAUSE appears
        if(flag == true && terminate == false)
        {
            g.setColor(Color.blue);
            g.setFont(new Font("Times Roman", Font.BOLD, Math.round(25/pixelSize)));
            g.drawString("PAUSE", iX(-170), iY(0));
            g.drawLine(iX(-200), iY(40), iX(-50), iY(40));
            g.drawLine(iX(-50), iY(40), iX(-50), iY(-20));
            g.drawLine(iX(-50), iY(-20), iX(-200), iY(-20));
            g.drawLine(iX(-200), iY(-20), iX(-200), iY(40));
        }

        poly1[0] = new Point2D(dl[i][0], dt[i][0]);
        poly1[1] = new Point2D(dl[i][0], db[i][0]);
        poly1[2] = new Point2D(dr[i][0], dt[i][0]);
        poly1[3] = new Point2D(dr[i][0], db[i][0]);

        poly2[0] = new Point2D(dl[i][1], dt[i][1]);
        poly2[1] = new Point2D(dl[i][1], db[i][1]);
        poly2[2] = new Point2D(dr[i][1], dt[i][1]);
        poly2[3] = new Point2D(dr[i][1], db[i][1]);

        poly3[0] = new Point2D(dl[i][2], dt[i][2]);
        poly3[1] = new Point2D(dl[i][2], db[i][2]);
        poly3[2] = new Point2D(dr[i][2], dt[i][2]);
        poly3[3] = new Point2D(dr[i][2], db[i][2]);

        poly4[0] = new Point2D(dl[i][3], dt[i][3]);
        poly4[1] = new Point2D(dl[i][3], db[i][3]);
        poly4[2] = new Point2D(dr[i][3], dt[i][3]);
        poly4[3] = new Point2D(dr[i][3], db[i][3]);

        if(inPoly)
        {
            int dx, dy;

            dy = 350 - dt[i][0];
            dx = (-160 - dl[i][0]);

            while(randomIndex3 == randomIndex2 || randomIndex3 == randomIndex)
            {
            	randomIndex3 = generator.nextInt(7);
            }
            switch(randomIndex3)
            {
                case 0:

                    dl[i][0]=-125-dx; dt[i][0]=350-dy; dr[i][0]=-90-dx; db[i][0]=315-dy;
                    dl[i][1]=-160-dx; dt[i][1]=350-dy; dr[i][1]=-125-dx; db[i][1]=315-dy;
                    dl[i][2]=-160-dx; dt[i][2]=315-dy; dr[i][2]=-125-dx; db[i][2]=280-dy;
                    dl[i][3]=-195-dx; dt[i][3]=315-dy; dr[i][3]=-160-dx; db[i][3]=280-dy;
                    fl[i][0]=dl[i][0]+1; ft[i][0]=dt[i][0]-1;
                    fl[i][1]=dl[i][1]+1; ft[i][1]=dt[i][1]-1;
                    fl[i][2]=dl[i][2]+1; ft[i][2]=dt[i][2]-1;
                    fl[i][3]=dl[i][3]+1; ft[i][3]=dt[i][3]-1;

                    g.drawRect(iX(dl[i][0]), iY(dt[i][0]), iX(dr[i][0]) - iX(dl[i][0]), iY(db[i][0]) - iY(dt[i][0]));
                    g.drawRect(iX(dl[i][1]), iY(dt[i][1]), iX(dr[i][1]) - iX(dl[i][1]), iY(db[i][1]) - iY(dt[i][1]));
                    g.drawRect(iX(dl[i][2]), iY(dt[i][2]), iX(dr[i][2]) - iX(dl[i][2]), iY(db[i][2]) - iY(dt[i][2]));
                    g.drawRect(iX(dl[i][3]), iY(dt[i][3]), iX(dr[i][3]) - iX(dl[i][3]), iY(db[i][3]) - iY(dt[i][3]));
                    g.setColor(Color.yellow);
                    g.fillRect(iX(fl[i][0]), iY(ft[i][0]), iX(dr[i][0]) - iX(fl[i][0]), iY(db[i][0]) - iY(ft[i][0]));
                    g.fillRect(iX(fl[i][1]), iY(ft[i][1]), iX(dr[i][1]) - iX(fl[i][1]), iY(db[i][1]) - iY(ft[i][1]));
                    g.fillRect(iX(fl[i][2]), iY(ft[i][2]), iX(dr[i][2]) - iX(fl[i][2]), iY(db[i][2]) - iY(ft[i][2]));
                    g.fillRect(iX(fl[i][3]), iY(ft[i][3]), iX(dr[i][3]) - iX(fl[i][3]), iY(db[i][3]) - iY(ft[i][3]));
                    break;

                case 1:

                    dl[i][0]=-195-dx; dt[i][0]=350-dy; dr[i][0]=-160-dx; db[i][0]=315-dy;
                    dl[i][1]=-160-dx; dt[i][1]=350-dy; dr[i][1]=-125-dx; db[i][1]=315-dy;
                    dl[i][2]=-160-dx; dt[i][2]=315-dy; dr[i][2]=-125-dx; db[i][2]=280-dy;
                    dl[i][3]=-125-dx; dt[i][3]=315-dy; dr[i][3]=-90-dx; db[i][3]=280-dy;
                    fl[i][0]=dl[i][0]+1; ft[i][0]=dt[i][0]-1;
                    fl[i][1]=dl[i][1]+1; ft[i][1]=dt[i][1]-1;
                    fl[i][2]=dl[i][2]+1; ft[i][2]=dt[i][2]-1;
                    fl[i][3]=dl[i][3]+1; ft[i][3]=dt[i][3]-1;

                    g.drawRect(iX(dl[i][0]), iY(dt[i][0]), iX(dr[i][0]) - iX(dl[i][0]), iY(db[i][0]) - iY(dt[i][0]));
                    g.drawRect(iX(dl[i][1]), iY(dt[i][1]), iX(dr[i][1]) - iX(dl[i][1]), iY(db[i][1]) - iY(dt[i][1]));
                    g.drawRect(iX(dl[i][2]), iY(dt[i][2]), iX(dr[i][2]) - iX(dl[i][2]), iY(db[i][2]) - iY(dt[i][2]));
                    g.drawRect(iX(dl[i][3]), iY(dt[i][3]), iX(dr[i][3]) - iX(dl[i][3]), iY(db[i][3]) - iY(dt[i][3]));
                    g.setColor(Color.magenta);
                    g.fillRect(iX(fl[i][0]), iY(ft[i][0]), iX(dr[i][0]) - iX(fl[i][0]), iY(db[i][0]) - iY(ft[i][0]));
                    g.fillRect(iX(fl[i][1]), iY(ft[i][1]), iX(dr[i][1]) - iX(fl[i][1]), iY(db[i][1]) - iY(ft[i][1]));
                    g.fillRect(iX(fl[i][2]), iY(ft[i][2]), iX(dr[i][2]) - iX(fl[i][2]), iY(db[i][2]) - iY(ft[i][2]));
                    g.fillRect(iX(fl[i][3]), iY(ft[i][3]), iX(dr[i][3]) - iX(fl[i][3]), iY(db[i][3]) - iY(ft[i][3]));
                    break;

                case 2:

                    dl[i][0]=-195-dx; dt[i][0]=350-dy; dr[i][0]=-160-dx; db[i][0]=315-dy;
                    dl[i][1]=-195-dx; dt[i][1]=315-dy; dr[i][1]=-160-dx; db[i][1]=280-dy;
                    dl[i][2]=-160-dx; dt[i][2]=315-dy; dr[i][2]=-125-dx; db[i][2]=280-dy;
                    dl[i][3]=-125-dx; dt[i][3]=315-dy; dr[i][3]=-90-dx; db[i][3]=280-dy;
                    fl[i][0]=dl[i][0]+1; ft[i][0]=dt[i][0]-1;
                    fl[i][1]=dl[i][1]+1; ft[i][1]=dt[i][1]-1;
                    fl[i][2]=dl[i][2]+1; ft[i][2]=dt[i][2]-1;
                    fl[i][3]=dl[i][3]+1; ft[i][3]=dt[i][3]-1;

                    g.drawRect(iX(dl[i][0]), iY(dt[i][0]), iX(dr[i][0]) - iX(dl[i][0]), iY(db[i][0]) - iY(dt[i][0]));
                    g.drawRect(iX(dl[i][1]), iY(dt[i][1]), iX(dr[i][1]) - iX(dl[i][1]), iY(db[i][1]) - iY(dt[i][1]));
                    g.drawRect(iX(dl[i][2]), iY(dt[i][2]), iX(dr[i][2]) - iX(dl[i][2]), iY(db[i][2]) - iY(dt[i][2]));
                    g.drawRect(iX(dl[i][3]), iY(dt[i][3]), iX(dr[i][3]) - iX(dl[i][3]), iY(db[i][3]) - iY(dt[i][3]));
                    g.setColor(Color.blue);
                    g.fillRect(iX(fl[i][0]), iY(ft[i][0]), iX(dr[i][0]) - iX(fl[i][0]), iY(db[i][0]) - iY(ft[i][0]));
                    g.fillRect(iX(fl[i][1]), iY(ft[i][1]), iX(dr[i][1]) - iX(fl[i][1]), iY(db[i][1]) - iY(ft[i][1]));
                    g.fillRect(iX(fl[i][2]), iY(ft[i][2]), iX(dr[i][2]) - iX(fl[i][2]), iY(db[i][2]) - iY(ft[i][2]));
                    g.fillRect(iX(fl[i][3]), iY(ft[i][3]), iX(dr[i][3]) - iX(fl[i][3]), iY(db[i][3]) - iY(ft[i][3]));
                    break;

                case 3:

                    dl[i][0]=-125-dx; dt[i][0]=350-dy; dr[i][0]=-90-dx; db[i][0]=315-dy;
                    dl[i][1]=-125-dx; dt[i][1]=315-dy; dr[i][1]=-90-dx; db[i][1]=280-dy;
                    dl[i][2]=-160-dx; dt[i][2]=315-dy; dr[i][2]=-125-dx; db[i][2]=280-dy;
                    dl[i][3]=-195-dx; dt[i][3]=315-dy; dr[i][3]=-160-dx; db[i][3]=280-dy;
                    fl[i][0]=dl[i][0]+1; ft[i][0]=dt[i][0]-1;
                    fl[i][1]=dl[i][1]+1; ft[i][1]=dt[i][1]-1;
                    fl[i][2]=dl[i][2]+1; ft[i][2]=dt[i][2]-1;
                    fl[i][3]=dl[i][3]+1; ft[i][3]=dt[i][3]-1;

                    g.drawRect(iX(dl[i][0]), iY(dt[i][0]), iX(dr[i][0]) - iX(dl[i][0]), iY(db[i][0]) - iY(dt[i][0]));
                    g.drawRect(iX(dl[i][1]), iY(dt[i][1]), iX(dr[i][1]) - iX(dl[i][1]), iY(db[i][1]) - iY(dt[i][1]));
                    g.drawRect(iX(dl[i][2]), iY(dt[i][2]), iX(dr[i][2]) - iX(dl[i][2]), iY(db[i][2]) - iY(dt[i][2]));
                    g.drawRect(iX(dl[i][3]), iY(dt[i][3]), iX(dr[i][3]) - iX(dl[i][3]), iY(db[i][3]) - iY(dt[i][3]));
                    g.setColor(Color.red);
                    g.fillRect(iX(fl[i][0]), iY(ft[i][0]), iX(dr[i][0]) - iX(fl[i][0]), iY(db[i][0]) - iY(ft[i][0]));
                    g.fillRect(iX(fl[i][1]), iY(ft[i][1]), iX(dr[i][1]) - iX(fl[i][1]), iY(db[i][1]) - iY(ft[i][1]));
                    g.fillRect(iX(fl[i][2]), iY(ft[i][2]), iX(dr[i][2]) - iX(fl[i][2]), iY(db[i][2]) - iY(ft[i][2]));
                    g.fillRect(iX(fl[i][3]), iY(ft[i][3]), iX(dr[i][3]) - iX(fl[i][3]), iY(db[i][3]) - iY(ft[i][3]));
                    break;

                case 4:

                    dl[i][0]=-160-dx; dt[i][0]=350-dy; dr[i][0]=-125-dx; db[i][0]=315-dy;
                    dl[i][1]=-160-dx; dt[i][1]=315-dy; dr[i][1]=-125-dx; db[i][1]=280-dy;
                    dl[i][2]=-125-dx; dt[i][2]=350-dy; dr[i][2]=-90-dx; db[i][2]=315-dy;
                    dl[i][3]=-125-dx; dt[i][3]=315-dy; dr[i][3]=-90-dx; db[i][3]=280-dy;
                    fl[i][0]=dl[i][0]+1; ft[i][0]=dt[i][0]-1;
                    fl[i][1]=dl[i][1]+1; ft[i][1]=dt[i][1]-1;
                    fl[i][2]=dl[i][2]+1; ft[i][2]=dt[i][2]-1;
                    fl[i][3]=dl[i][3]+1; ft[i][3]=dt[i][3]-1;

                    g.drawRect(iX(dl[i][0]), iY(dt[i][0]), iX(dr[i][0]) - iX(dl[i][0]), iY(db[i][0]) - iY(dt[i][0]));
                    g.drawRect(iX(dl[i][1]), iY(dt[i][1]), iX(dr[i][1]) - iX(dl[i][1]), iY(db[i][1]) - iY(dt[i][1]));
                    g.drawRect(iX(dl[i][2]), iY(dt[i][2]), iX(dr[i][2]) - iX(dl[i][2]), iY(db[i][2]) - iY(dt[i][2]));
                    g.drawRect(iX(dl[i][3]), iY(dt[i][3]), iX(dr[i][3]) - iX(dl[i][3]), iY(db[i][3]) - iY(dt[i][3]));
                    g.setColor(Color.green);
                    g.fillRect(iX(fl[i][0]), iY(ft[i][0]), iX(dr[i][0]) - iX(fl[i][0]), iY(db[i][0]) - iY(ft[i][0]));
                    g.fillRect(iX(fl[i][1]), iY(ft[i][1]), iX(dr[i][1]) - iX(fl[i][1]), iY(db[i][1]) - iY(ft[i][1]));
                    g.fillRect(iX(fl[i][2]), iY(ft[i][2]), iX(dr[i][2]) - iX(fl[i][2]), iY(db[i][2]) - iY(ft[i][2]));
                    g.fillRect(iX(fl[i][3]), iY(ft[i][3]), iX(dr[i][3]) - iX(fl[i][3]), iY(db[i][3]) - iY(ft[i][3]));
                    break;

                case 5:

                    dl[i][0]=-160-dx; dt[i][0]=350-dy; dr[i][0]=-125-dx; db[i][0]=315-dy;
                    dl[i][1]=-195-dx; dt[i][1]=315-dy; dr[i][1]=-160-dx; db[i][1]=280-dy;
                    dl[i][2]=-160-dx; dt[i][2]=315-dy; dr[i][2]=-125-dx; db[i][2]=280-dy;
                    dl[i][3]=-125-dx; dt[i][3]=315-dy; dr[i][3]=-90-dx; db[i][3]=280-dy;
                    fl[i][0]=dl[i][0]+1; ft[i][0]=dt[i][0]-1;
                    fl[i][1]=dl[i][1]+1; ft[i][1]=dt[i][1]-1;
                    fl[i][2]=dl[i][2]+1; ft[i][2]=dt[i][2]-1;
                    fl[i][3]=dl[i][3]+1; ft[i][3]=dt[i][3]-1;

                    g.drawRect(iX(dl[i][0]), iY(dt[i][0]), iX(dr[i][0]) - iX(dl[i][0]), iY(db[i][0]) - iY(dt[i][0]));
                    g.drawRect(iX(dl[i][1]), iY(dt[i][1]), iX(dr[i][1]) - iX(dl[i][1]), iY(db[i][1]) - iY(dt[i][1]));
                    g.drawRect(iX(dl[i][2]), iY(dt[i][2]), iX(dr[i][2]) - iX(dl[i][2]), iY(db[i][2]) - iY(dt[i][2]));
                    g.drawRect(iX(dl[i][3]), iY(dt[i][3]), iX(dr[i][3]) - iX(dl[i][3]), iY(db[i][3]) - iY(dt[i][3]));
                    g.setColor(Color.orange);
                    g.fillRect(iX(fl[i][0]), iY(ft[i][0]), iX(dr[i][0]) - iX(fl[i][0]), iY(db[i][0]) - iY(ft[i][0]));
                    g.fillRect(iX(fl[i][1]), iY(ft[i][1]), iX(dr[i][1]) - iX(fl[i][1]), iY(db[i][1]) - iY(ft[i][1]));
                    g.fillRect(iX(fl[i][2]), iY(ft[i][2]), iX(dr[i][2]) - iX(fl[i][2]), iY(db[i][2]) - iY(ft[i][2]));
                    g.fillRect(iX(fl[i][3]), iY(ft[i][3]), iX(dr[i][3]) - iX(fl[i][3]), iY(db[i][3]) - iY(ft[i][3]));
                    break;

                case 6:

                    dl[i][0]=-195-dx; dt[i][0]=350-dy; dr[i][0]=-160-dx; db[i][0]=315-dy;
                    dl[i][1]=-160-dx; dt[i][1]=350-dy; dr[i][1]=-125-dx; db[i][1]=315-dy;
                    dl[i][2]=-125-dx; dt[i][2]=350-dy; dr[i][2]=-90-dx; db[i][2]=315-dy;
                    dl[i][3]=-90-dx; dt[i][3]=350-dy; dr[i][3]=-55-dx; db[i][3]=315-dy;
                    fl[i][0]=dl[i][0]+1; ft[i][0]=dt[i][0]-1;
                    fl[i][1]=dl[i][1]+1; ft[i][1]=dt[i][1]-1;
                    fl[i][2]=dl[i][2]+1; ft[i][2]=dt[i][2]-1;
                    fl[i][3]=dl[i][3]+1; ft[i][3]=dt[i][3]-1;

                    g.drawRect(iX(dl[i][0]), iY(dt[i][0]), iX(dr[i][0]) - iX(dl[i][0]), iY(db[i][0]) - iY(dt[i][0]));
                    g.drawRect(iX(dl[i][1]), iY(dt[i][1]), iX(dr[i][1]) - iX(dl[i][1]), iY(db[i][1]) - iY(dt[i][1]));
                    g.drawRect(iX(dl[i][2]), iY(dt[i][2]), iX(dr[i][2]) - iX(dl[i][2]), iY(db[i][2]) - iY(dt[i][2]));
                    g.drawRect(iX(dl[i][3]), iY(dt[i][3]), iX(dr[i][3]) - iX(dl[i][3]), iY(db[i][3]) - iY(dt[i][3]));
                    g.setColor(Color.cyan);
                    g.fillRect(iX(fl[i][0]), iY(ft[i][0]), iX(dr[i][0]) - iX(fl[i][0]), iY(db[i][0]) - iY(ft[i][0]));
                    g.fillRect(iX(fl[i][1]), iY(ft[i][1]), iX(dr[i][1]) - iX(fl[i][1]), iY(db[i][1]) - iY(ft[i][1]));
                    g.fillRect(iX(fl[i][2]), iY(ft[i][2]), iX(dr[i][2]) - iX(fl[i][2]), iY(db[i][2]) - iY(ft[i][2]));
                    g.fillRect(iX(fl[i][3]), iY(ft[i][3]), iX(dr[i][3]) - iX(fl[i][3]), iY(db[i][3]) - iY(ft[i][3]));
                    break;
            }
            score -= level * M;
            randomIndex = randomIndex3;
            color[i] = randomIndex;
            inPoly = false;
        }

        if(terminate == true)
        {
            g.setColor(Color.red);
            g.setFont(new Font("Times Roman", Font.BOLD, Math.round(18/pixelSize)));
            g.drawString("GAME OVER", iX(-180), iY(0));
            g.drawLine(iX(-200), iY(40), iX(-50), iY(40));
            g.drawLine(iX(-50), iY(40), iX(-50), iY(-20));
            g.drawLine(iX(-50), iY(-20), iX(-200), iY(-20));
            g.drawLine(iX(-200), iY(-20), iX(-200), iY(40));
        }

        try
        {
            Thread.sleep(FS);
        }
        catch(InterruptedException ex){}

        if(terminate == false)
            repaint();
    }
}