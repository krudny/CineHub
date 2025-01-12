export const checkAdminPriviliges = async (setIsAuthenticated: React.Dispatch<React.SetStateAction<boolean>>) => {
    try {
      const response = await fetch("http://localhost:8080/session/check-admin-privileges", {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
        credentials: "include",
      });
  
      if (!response.ok) {
        return;
      }
  
      const data = await response.json();
      console.log("success");
      console.log(response);
      setIsAuthenticated(data.isAuthenticated);
    } catch (error) {
        //do nothing
    }
  };