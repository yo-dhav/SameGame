<h1>SameGame AI</h1>

<p>A Java Swing-based desktop application that implements the classic tile-matching puzzle game <strong>SameGame</strong>. This project features a playable solo mode alongside multiple AI strategies that can play the game autonomously or serve as a hint system for the user.</p>

<h2>&#x1F4F8; Screenshots</h2>
<p align="center">
  <img src="images/Screenshot 2026-04-05 113029.png" alt="SameGame Gameplay Board" width="45%">
  &nbsp;&nbsp;
  <img src="images/Screenshot 2026-04-05 113205.png" alt="AI Hint System in Action" width="45%">
    
  &nbsp;&nbsp;
  <img src="images/Screenshot 2026-04-05 113241.png" alt="AI Hint System in Action" width="45%">
</p>

<h2>&#x1F3AE; Game Features</h2>
<ul>
    <li><strong>Interactive GUI</strong>: Built entirely with Java Swing, featuring dynamic board resizing and color rendering.</li>
    <li><strong>Customizable Gameplay</strong>: 
        <ul>
            <li>Grid dimensions can be adjusted from 2x2 up to 50x50 (default is 10x20).</li>
            <li>Number of tile colors can be configured between 3 and 7 (default is 4).</li>
        </ul>
    </li>
    <li><strong>Scoring System</strong>: Points are awarded based on the size of the cleared block using the formula <code>(block size - 1)^2</code>.</li>
    <li><strong>Physics Engine</strong>: Includes gravity logic where blocks fall downward to fill empty spaces when tiles are removed, and columns shift left if a column is entirely cleared.</li>
</ul>

<hr>

<h2>&#x1F9E0; AI Strategy Analysis</h2>
<p>The project uses the <strong>Strategy Design Pattern</strong> to seamlessly switch between different AI solvers.</p>
<p><em>Note on baseline complexity: Generating all valid moves requires scanning the board, which takes <strong>O(N)</strong> time, where <strong>N</strong> is the total number of tiles (Rows &times; Cols). Let <strong>M</strong> be the number of valid moves found.</em></p>

<h3>1. Greedy Strategy</h3>
<p>Evaluates the board to locate the single highest-scoring immediate move.</p>
<table>
    <thead>
        <tr>
            <th style="text-align: left;">Feature</th>
            <th style="text-align: left;">Description</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td style="text-align: left;"><strong>Class</strong></td>
            <td style="text-align: left;"><code>GreedyStrategy.java</code></td>
        </tr>
        <tr>
            <td style="text-align: left;"><strong>Algorithm</strong></td>
            <td style="text-align: left;">Custom Merge Sort</td>
        </tr>
        <tr>
            <td style="text-align: left;"><strong>Heuristic</strong></td>
            <td style="text-align: left;">Maximizes immediate point gain (<code>(block size - 1)^2</code>) without looking ahead.</td>
        </tr>
        <tr>
            <td style="text-align: left;"><strong>Time Complexity</strong></td>
            <td style="text-align: left;"><strong>O(N + M log M)</strong>. Finding moves takes O(N), and the custom merge sort processes the M valid moves in O(M log M) time.</td>
        </tr>
    </tbody>
</table>

<h3>2. Divide and Conquer (UKDAC)</h3>
<p>An alternative to sorting, this approach recursively divides the list of moves to find the mathematical maximum.</p>
<table>
    <thead>
        <tr>
            <th style="text-align: left;">Feature</th>
            <th style="text-align: left;">Description</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td style="text-align: left;"><strong>Class</strong></td>
            <td style="text-align: left;"><code>UKDAC.java</code></td>
        </tr>
        <tr>
            <td style="text-align: left;"><strong>Algorithm</strong></td>
            <td style="text-align: left;">Recursive Array Halving</td>
        </tr>
        <tr>
            <td style="text-align: left;"><strong>Heuristic</strong></td>
            <td style="text-align: left;">Same as Greedy; looks for the single highest-scoring immediate move.</td>
        </tr>
        <tr>
            <td style="text-align: left;"><strong>Time Complexity</strong></td>
            <td style="text-align: left;"><strong>O(N)</strong>. Finding moves takes O(N). The recursive search forms a binary tree over M elements, taking O(M) time. Since M &le; N, it simplifies to O(N).</td>
        </tr>
    </tbody>
</table>

<h3>3. Backtracking (Minimax)</h3>
<p>The most advanced strategy. It simulates future turns to maximize the player's score while assuming an "opponent" minimizes it.</p>
<table>
    <thead>
        <tr>
            <th style="text-align: left;">Feature</th>
            <th style="text-align: left;">Description</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td style="text-align: left;"><strong>Class</strong></td>
            <td style="text-align: left;"><code>BackTrackStratergyOPP.java</code></td>
        </tr>
        <tr>
            <td style="text-align: left;"><strong>Algorithm</strong></td>
            <td style="text-align: left;">Minimax with Alpha-Beta Pruning</td>
        </tr>
        <tr>
            <td style="text-align: left;"><strong>Depth Limit</strong></td>
            <td style="text-align: left;">Capped at <code>MAX_DEPTH = 2</code> to maintain performance.</td>
        </tr>
        <tr>
            <td style="text-align: left;"><strong>Optimization</strong></td>
            <td style="text-align: left;">Pre-sorts moves descending before exploration to increase Alpha-Beta pruning efficiency.</td>
        </tr>
        <tr>
            <td style="text-align: left;"><strong>Time Complexity</strong></td>
            <td style="text-align: left;">Worst-case <strong>O(M&sup2; * N)</strong>. Evaluates up to M&sup2; nodes at depth 2. Each simulated node requires copying the board and calculating gravity/new moves (O(N)).</td>
        </tr>
    </tbody>
</table>

<h3>4. Random / Beginner</h3>
<p>A simplistic fallback strategy designed to be unpredictable.</p>
<table>
    <thead>
        <tr>
            <th style="text-align: left;">Feature</th>
            <th style="text-align: left;">Description</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td style="text-align: left;"><strong>Class</strong></td>
            <td style="text-align: left;"><code>RandomStrategy.java</code></td>
        </tr>
        <tr>
            <td style="text-align: left;"><strong>Algorithm</strong></td>
            <td style="text-align: left;">Uniform Random Selection</td>
        </tr>
        <tr>
            <td style="text-align: left;"><strong>Heuristic</strong></td>
            <td style="text-align: left;">None. Ignores scores and board states completely.</td>
        </tr>
        <tr>
            <td style="text-align: left;"><strong>Time Complexity</strong></td>
            <td style="text-align: left;"><strong>O(N)</strong>. Finding all valid moves takes O(N), and selecting a random index from the list takes O(1).</td>
        </tr>
    </tbody>
</table>

<hr>

<h2>&#x1F3D7;&#xFE0F; Architecture</h2>
<ul>
    <li><code>MainClass.java</code>: The core entry point managing the Swing GUI, menus, event listeners, and game state.</li>
    <li><code>GameEngine.java</code>: A static utility hub responsible for physics (<code>applyGravity</code>), move validation (<code>findBlock</code>, <code>getAllMoves</code>), and win/loss condition checking (<code>isGameOver</code>).</li>
    <li><code>AIPlayer.java</code>: A dispatcher class that maps the user's selected UI integer mode to the specific <code>AIStrategy</code> object implementation.</li>
    <li><code>AIStrategy.java</code>: The core interface defining the <code>getMove(char[][] board)</code> contract.</li>
</ul>

<h2>&#x1F680; How to Run</h2>
<ol>
    <li>Ensure you have the Java Development Kit (JDK 14+) installed on your machine.</li>
    <li>Compile the Java files from the root of your project directory:
<pre><code class="language-bash">javac mat/unical/it/embasp/samegame/*.java</code></pre>
    </li>
    <li>Run the application:
<pre><code class="language-bash">java mat.unical.it.embasp.samegame.MainClass</code></pre>
    </li>
</ol>
