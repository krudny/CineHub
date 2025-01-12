"use client";

import { useEffect, useState } from "react";
import Link from "next/link";
import { useRouter } from "next/navigation";
import { logout } from "@/app/utils/functions";
import { checkAuth } from "../auth/roleVerificators/checkAuth";
import { checkAdminPriviliges } from "../auth/roleVerificators/hasAdminPrivileges";
import { checkEmployeePriviliges } from "../auth/roleVerificators/hasEmployeePrivileges";

export default function Navbar() {
  const router = useRouter();
  

  const [isAuthenticated, setIsAuthenticated] = useState(false);

  const [isAdmin, setIsAdmin] = useState(false);

  const [isEmployee, setIsEmployee] = useState(false);


  useEffect(() => {
    checkAdminPriviliges(setIsAdmin);
  }, []);

  useEffect(() => {
   checkAuth(setIsAuthenticated);
  }, []);

  useEffect(() => {
    checkEmployeePriviliges(setIsEmployee);
  }, []);

  const handleLogout = async () => {
      await logout(router);
      setIsAuthenticated(false);
      setIsAdmin(false);
      setIsEmployee(false);
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
        {isAdmin && (
        <p className="relative group">
          Admin menu
          <span className="absolute left-0 -bottom-1 w-0 h-[2px] bg-neutral-100 transition-all duration-300 group-hover:w-full"></span>
        </p>
        )}

      {isEmployee && (
        <p className="relative group">
          Employee menu
          <span className="absolute left-0 -bottom-1 w-0 h-[2px] bg-neutral-100 transition-all duration-300 group-hover:w-full"></span>
        </p>
        )}
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