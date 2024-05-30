/* Variable scope refers to the visibility and accessibility of variables within a program. 
In JavaScript, variable scope is determined by the type of variable declaration used, which can be `let`, `var`, or `const`. 
Each type of declaration has its own rules regarding scope, reassignment, and hoisting.

var Variables:

Variables declared with var have function-level scope. This means they are accessible throughout the function in which they are declared, including any nested functions.
If a var variable is declared outside of any function, it becomes a global variable and is accessible throughout the entire script, including within functions.
var variables can be redeclared and updated within their scope.
*/

// Global variable

// TODO: Create a variable to demonstrate global scope and assign it with "I am a global scope variable!".

// Function to demonstrate global scope
function globalScopeDemo() {
    // Access globalVar inside the function
    let outputElement = document.getElementById("output-global");
    outputElement.textContent = "Replace me with the variable you've created.";
}

function localScopeDemo() {
    // Local variable (only accessible within this function)
   
    // TODO: Create a variable to demonstrate local scope and assign it with "I am a local scope variable declared using the let keyword!".

    // Access localVarLet inside the function
    let outputElement = document.getElementById("output-local-let");
    outputElement.textContent = "Replace me with the variable you've created.";

    // Attempt to reassign localVarLet (this is allowed)
    
    "Replace me with the variable you've created" = "Trying to reassign with let"; 
    console.log("Replace me with the variable you've created."); // Output to console to demonstrate reassignment

    // Attempt to redeclare localVarLet (this will throw an error)
    // let localVarLet = "Trying to redeclare with let"; // Uncomment to see error
}

function varScopeDemo() {
    // Local variable (function-scoped)

    // TODO: Create a variable to demonstrate local scope and assign it with "I am a local scope variable declared using the var keyword!".

    // Access the variable inside the function
    let outputElement = document.getElementById("output-local-var");
    outputElement.textContent = "Replace me with the variable you've created.";

    // Reassigning the local variable (this is allowed)
    "Replace me with the variable you've created." = "I have been reassigned with a different value!";

    // Access re-assigned Variable
    let outputReassigned = document.getElementById("output-reassigned-var");
    outputReassigned.textContent = "Replace me with the variable you've created.";

    // Demonstrate function-scoped variable
    if (true) {
        var insideIfVar = "I am inside an if block but function-scoped!";
    }
    console.log(insideIfVar); // This will work as insideIfVar is function-scoped

    // Redeclare the created variable (this is allowed with var)
    "Replace me with the variable you've created." = "I am redeclared with var!";
    console.log("Replace me with the variable you've created."); // Output the redeclared variable
}

function blockScopeDemo() {
    if (true) {
        // Block-scoped variable
        const blockVarConst = "I am a block-level scope variable declared using the const keyword!";

        // Access blockVar inside the block
        let outputElement = document.getElementById("output-block-const");
        outputElement.textContent = blockVarConst;

        // Attempt to reassign blockVar (will throw TypeError as reassignment is not allowed)
        // blockVarConst = "Trying to reassign with const"; // Uncomment to see error
    }
    // console.log(blockVarConst); // This will throw an error as blockVarConst is block-scoped
}

function mixedScopeDemo() {
    let outerLet = "Outer let variable";

    if (true) {
        let innerLet = "Inner let variable";
        var innerVar = "Inner var variable";
        
        // Accessing outerLet inside block
        console.log(outerLet); // This will work as outerLet is in the outer scope

        // Accessing innerLet and innerVar inside block
        console.log(innerLet); // This will work
        console.log(innerVar); // This will work
    }

    // Accessing innerLet and innerVar outside block
    // console.log(innerLet); // This will throw an error as innerLet is block-scoped
   // console.log(innerVar); // This will work as innerVar is function-scoped
}

// Call functions to update the HTML
globalScopeDemo();
localScopeDemo();
varScopeDemo();
blockScopeDemo();
mixedScopeDemo();
