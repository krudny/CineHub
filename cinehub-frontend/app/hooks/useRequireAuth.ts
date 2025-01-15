"use client";
import { AppRouterInstance } from "next/dist/shared/lib/app-router-context.shared-runtime";
import { useEffect } from "react";
import toast from "react-hot-toast";
import { isAuthenticatedAsync } from "../auth/authFunctions";

export function useRequireAuth(router: AppRouterInstance, requiredRole: string = "USER", redirectTo = "/") {
  useEffect(() => {
    async function checkAuthentication() {
      try {
        const isAuthenticated = await isAuthenticatedAsync(requiredRole);
        if (!isAuthenticated) {
          router.push(redirectTo);
          toast.error("You don't have enough privileges to visit this site!");
        }
      } catch (error) {
        toast.error("Authentication error:" + error);
      }
    }

    checkAuthentication();
  }, [router, requiredRole, redirectTo]);
}
