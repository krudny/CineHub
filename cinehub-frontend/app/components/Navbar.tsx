"use client";

import { useEffect, useState } from "react"; // <-- Add this import
import Link from "next/link";
import { useRouter } from "next/navigation";
import { logout } from "@/app/utils/functions";

export default function Navbar() {
  const router = useRouter();
  
  // Add state to track authentication
  const [isAuthenticated, setIsAuthenticated] = useState(false);

  useEffect(() => {
    const checkAuth = async () => {
      try {
        const response = await fetch("http://localhost:8080/session/check-auth", {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
          },
          credentials: "include",
        });
  
        if (!response.ok) {
          throw new Error("Failed to fetch");
        }
  
        const data = await response.json();
        console.log("success");
        console.log(response);
        setIsAuthenticated(data.isAuthenticated);
      } catch (error) {
        console.error("Error during authentication check:", error);
      }
    };
  
    checkAuth();
  }, []);

  const handleLogout = async () => {
    try {
      await logout(router);
      setIsAuthenticated(false);
    } catch (error) {
      console.error("Błąd podczas wylogowywania:", error);
    }
  };

  return (
    <div className="w-full flex flex-col md:flex-row justify-between items-center text-neutral-100 px-16">
      <Link href="/">
        <div className="flex py-6 font-oswald text-2xl md:text-4xl">
          <p className="p-1 md:py-1.5">Cine</p>
          <p className="bg-orange-500 rounded-lg md:mx-1 p-1 md:p-1.5">Hub</p>
        </div>
      </Link>
      <div className="flex items-center font-oswald text-md md:text-2xl gap-x-4 sm:gap-x-10">
        <p className="relative group">
          Menu 1
          <span className="absolute left-0 -bottom-1 w-0 h-[2px] bg-neutral-100 transition-all duration-300 group-hover:w-full"></span>
        </p>
        {/* Conditionally render links based on authentication state */}
        {isAuthenticated ? (
          <p
            className="relative group cursor-pointer"
            onClick={handleLogout}
          >
            Logout
            <span className="absolute left-0 -bottom-1 w-0 h-[2px] bg-neutral-100 transition-all duration-300 group-hover:w-full"></span>
          </p>
        ) : (
          <>
            <Link href="/auth/login">
              <p className="relative group">
                Login
                <span className="absolute left-0 -bottom-1 w-0 h-[2px] bg-neutral-100 transition-all duration-300 group-hover:w-full"></span>
              </p>
            </Link>
            <Link href="/auth/register">
              <p className="relative group">
                Register
                <span className="absolute left-0 -bottom-1 w-0 h-[2px] bg-neutral-100 transition-all duration-300 group-hover:w-full"></span>
              </p>
            </Link>
          </>
        )}
      </div>
    </div>
  );
}